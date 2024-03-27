package proiect.service;

import proiect.model.Echipa;
import proiect.model.Meci;
import proiect.repository.EchipaRepository;
import proiect.repository.MeciRepository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CampionatService {
    private EchipaRepository echipaRepository;
    private MeciRepository meciRepository;

    public CampionatService() {
        this.echipaRepository = new EchipaRepository();
        this.meciRepository = new MeciRepository();
    }

    public Map<Echipa, Integer> vizualizeazaClasament() {
        List<Echipa> echipe = echipaRepository.findAllEchipa();
        List<Meci> meciuri = meciRepository.findAllMeci();

        Map<Echipa, Integer> clasament = new HashMap<>();
        for (Echipa echipa : echipe) {
            clasament.put(echipa, 0);
        }

        for (Meci meci : meciuri) {
            Echipa echipa1 = meci.getEchipa1();
            Echipa echipa2 = meci.getEchipa2();
            int puncteEchipa1 = clasament.getOrDefault(echipa1, 0);
            int puncteEchipa2 = clasament.getOrDefault(echipa2, 0);
            if (meci.getScor1() > meci.getScor2()) {
                clasament.put(echipa1, puncteEchipa1 + 3);
            } else if (meci.getScor1() < meci.getScor2()) {
                clasament.put(echipa2, puncteEchipa2 + 3);
            } else {
                clasament.put(echipa1, puncteEchipa1 + 1);
                clasament.put(echipa2, puncteEchipa2 + 1);
            }
        }

        return clasament.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }
}