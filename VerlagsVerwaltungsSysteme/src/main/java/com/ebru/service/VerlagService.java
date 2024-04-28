package com.ebru.service;

import com.ebru.model.Autor;
import com.ebru.model.Buch;
import com.ebru.model.Verlag;
import com.ebru.model.dto.BuchDetailInfo;
import com.ebru.model.dto.VerlagDetailInfo;
import com.ebru.repository.AutorRepository;
import com.ebru.repository.BuchRepository;
import com.ebru.repository.VerlagRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VerlagService {

    private final VerlagRepository verlagRepository;
    private final BuchRepository buchRepository;
    private final AutorRepository autorRepository;

    public VerlagService(VerlagRepository verlagRepository, BuchRepository buchRepository, AutorService autorService, AutorRepository autorRepository) {
        this.verlagRepository = verlagRepository;
        this.buchRepository = buchRepository;
        this.autorRepository = autorRepository;
    }

    public List<Verlag> getAllVerlage(){
        return verlagRepository.findAll();
    }

    public List<VerlagDetailInfo> getAllVerlageWithBuecherAndAutoren(){
        List<Verlag> verlage = getAllVerlage();
        List<VerlagDetailInfo> verlagdetailList = new ArrayList<>();

        for (Verlag verlag : verlage) {
            VerlagDetailInfo vertrag_info = VerlagDetailInfo.builder()
                    .verlagID(verlag.getVerlagID())
                    .VerlagName(verlag.getVerlagName()).build();
            verlagdetailList.add(vertrag_info);
        }


        for (VerlagDetailInfo verlagDetailInfo : verlagdetailList) {
            List<Buch> buecher = buchRepository.findBuecherByVerlagID(verlagDetailInfo.getVerlagID());
            List<BuchDetailInfo> buchDetailInfoList = new ArrayList<>();

            for (Buch book : buecher) {
                BuchDetailInfo buch_info = BuchDetailInfo.builder()
                        .verlagID(book.getVerlagID())
                        .titel(book.getTitel())
                        .preis(book.getPreis())
                        .isbn(book.getIsbn())
                        .buchID(book.getBuchID()).build();
                buchDetailInfoList.add(buch_info);
            }
            for (BuchDetailInfo buchDetailInfo : buchDetailInfoList) {
                List<Autor> autoren = autorRepository.findAutorenByBuchID(buchDetailInfo.getBuchID());

                buchDetailInfo.setAutoren(autoren);
            }
            verlagDetailInfo.setBuecher(buchDetailInfoList);
        }
        return verlagdetailList;
    }


    public VerlagDetailInfo getVerlagByIdWithBooksAndAuthors(Long verlagId){

        Optional<Verlag> verlagOpt = verlagRepository.findVerlageByVerlagID(verlagId);

        VerlagDetailInfo verlagDetailInfo = null;

        if(verlagOpt.isPresent()){
            Verlag verlagObj = verlagOpt.get();
            verlagDetailInfo = VerlagDetailInfo.builder()
                    .verlagID(verlagObj.getVerlagID())
                    .VerlagName(verlagObj.getVerlagName()).build();

            List<Buch> buecher = buchRepository.findBuecherByVerlagID(verlagDetailInfo.getVerlagID());
            List<BuchDetailInfo> buchDetailInfoList = new ArrayList<>();

            for (Buch book : buecher) {
                BuchDetailInfo buch_info = BuchDetailInfo.builder()
                        .verlagID(book.getVerlagID())
                        .titel(book.getTitel())
                        .preis(book.getPreis())
                        .isbn(book.getIsbn())
                        .buchID(book.getBuchID()).build();
                buchDetailInfoList.add(buch_info);
            }
            for (BuchDetailInfo buchDetailInfo : buchDetailInfoList) {
                List<Autor> autoren = autorRepository.findAutorenByBuchID(buchDetailInfo.getBuchID());

                buchDetailInfo.setAutoren(autoren);
            }
            verlagDetailInfo.setBuecher(buchDetailInfoList);

        }

        return verlagDetailInfo;
    }

}
