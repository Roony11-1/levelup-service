package com.patitofeliz.levelup_service.service.gacha;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.patitofeliz.levelup_service.model.gacha.Banner;
import com.patitofeliz.levelup_service.model.gacha.BannerItem;
import com.patitofeliz.levelup_service.model.gacha.Pull;
import com.patitofeliz.levelup_service.model.usuario.Usuario;
import com.patitofeliz.levelup_service.repository.gacha.BannerRepository;
import com.patitofeliz.levelup_service.repository.gacha.PullRepository;
import com.patitofeliz.levelup_service.repository.usuario.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GachaService 
{
    private final BannerRepository bannerRepository;
    private final PullRepository pullRepository;
    private final UsuarioRepository usuarioRepository;

    private static final Map<String, Double> PROB_RAREZA = Map.of(
            "B", 60.0,
            "A", 30.0,
            "S", 10.0
    );

    public Pull pull(int bannerId, int usuarioId) 
    {
        Banner banner = bannerRepository.findById(bannerId)
                .orElseThrow(() -> new RuntimeException("Banner no encontrado"));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // tendre rarezas B - A - S
        // Lista de objetos de el banner
        List<BannerItem> listaBanner = banner.getItems();

        if (listaBanner.isEmpty())
            throw new RuntimeException("El banner no tiene items asignados");

        // Metodo auxiliar par aescoger el item de el banner
        BannerItem itemGanado = recompenza(listaBanner);

        Pull pull = new Pull();
        pull.setBanner(banner);
        pull.setUsuario(usuario);
        pull.setNombre(itemGanado.getNombre());
        pull.setClase(itemGanado.getClase());
        pull.setTipo(itemGanado.getTipo());
        pull.setRareza(itemGanado.getRareza());

        return pullRepository.save(pull);
    }

    private BannerItem recompenza(List<BannerItem> items)
    {
        String rarezaElegida = elegirRareza();

        List<BannerItem> filtrados = items.stream()
                .filter(i -> i.getRareza().equalsIgnoreCase(rarezaElegida))
                .toList();

        if (filtrados.isEmpty())
            throw new RuntimeException("No hay items con rareza " + rarezaElegida);

        return elegirDentroRareza(filtrados);
}

    private String elegirRareza()
    {
        double total = PROB_RAREZA.values().stream().mapToDouble(Double::doubleValue).sum();
        double rnd = Math.random() * total;

        double acumulado = 0;
        for (var entry : PROB_RAREZA.entrySet()) 
        {
            acumulado += entry.getValue();
            if (rnd < acumulado)
                return entry.getKey();
        }
        return "B"; // fallback
    }

    private BannerItem elegirDentroRareza(List<BannerItem> itemsDeRareza)
    {
        double total = itemsDeRareza.stream()
                .mapToDouble(BannerItem::getProbabilidad)
                .sum();

        double rnd = Math.random() * total;

        double acumulado = 0;
        for (BannerItem item : itemsDeRareza) 
        {
            acumulado += item.getProbabilidad();
            if (rnd < acumulado)
                return item;
        }

        return itemsDeRareza.get(0);
    }
}
