/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.livrari.utilitare;

import java.util.ArrayList;
import java.util.List;
import ro.papetti.livrari.model.ComandaPluPoz;
import ro.papetti.pluriva.entity.POrderPoz;
import ro.papetti.pluriva.entity.SOrderPoz;

/**
 *
 * @author MariusO
 */
public final class  Transformers {

    private Transformers() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    
    public static List<ComandaPluPoz> getComandaPluPozFromS(List<SOrderPoz> listPlu){
        List listPoz = new ArrayList<ComandaPluPoz>();
        if (listPlu==null)
            return listPoz;
        for (SOrderPoz pozPlu : listPlu) {
            ComandaPluPoz liniePoz = new ComandaPluPoz(pozPlu);
            //doar liniile care tin de comanda nu si intrarile
            if (pozPlu.getSOrderPozParentId()==null)
                listPoz.add(liniePoz);
        }
                
        return listPoz;
    }
    
        public static List<ComandaPluPoz> getComandaPluPozFromP(List<POrderPoz> listPlu){
        List listPoz = new ArrayList<ComandaPluPoz>();
        if (listPlu==null)
            return listPoz;
            
        for (POrderPoz pozPlu : listPlu) {
            ComandaPluPoz liniePoz = new ComandaPluPoz(pozPlu);
            listPoz.add(liniePoz);
        }
                
        return listPoz;
    }
}
