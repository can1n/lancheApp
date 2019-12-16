/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("lanches")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ServiceLanche {
	public List<Lanche> listalanches;
        private static double valorAlface = 0.40;
        private static double valorBacon = 2.00;
        private static double valorHamburguer = 3;
        private static double valorOvo = 0.8;
        private static double valorQueijo = 1.5;
	private static Ingredientes alface;
	private static Ingredientes bacon;
	private static Ingredientes hamburguer;
	private static Ingredientes ovo;
	private static Ingredientes queijo;
        
    public ServiceLanche() {
        
    }
    public void criarLanches(){
        String descLanche = null;
        listalanches = new ArrayList();
        alface = new Ingredientes(1,"Alface",valorAlface,1);
        bacon = new Ingredientes(2,"Bacon",valorBacon,1);
        hamburguer = new Ingredientes(3,"Hamburguer de Carne",valorHamburguer,1);
        ovo = new Ingredientes(4,"Ovo",valorOvo,1);
        queijo = new Ingredientes(5,"Queijo",valorQueijo,1);
       
        
        for (int i = 0; i <= 4; i++) {
            List<Ingredientes> IngredientesLanche = new ArrayList<>();
            switch (i) {
                case 0:
                    IngredientesLanche.add(bacon);
                    IngredientesLanche.add(hamburguer);
                    IngredientesLanche.add(queijo);
                    descLanche = "X-Bacon";
                    break;

                case 1:
                    IngredientesLanche.add(hamburguer);
                    IngredientesLanche.add(queijo);
                    descLanche = "X-Burguer";
                    break;
                case 2:
                    IngredientesLanche.add(ovo);
                    IngredientesLanche.add(hamburguer);
                    IngredientesLanche.add(queijo);
                    descLanche = "X-Egg";
                    break;
                case 3:
                    IngredientesLanche.add(bacon);
                    IngredientesLanche.add(ovo);
                    IngredientesLanche.add(hamburguer);
                    IngredientesLanche.add(queijo);
                    descLanche = "X-Egg Bacon";
                    break;
                case 4:
                    IngredientesLanche.add(alface);
                    IngredientesLanche.add(hamburguer);
                    IngredientesLanche.add(queijo);
                    descLanche = "X-Salada";
                    break;
            }
		Lanche lanche = new Lanche(i, descLanche, IngredientesLanche, 0.0);
            
            listalanches.add(lanche);
        }
    }
    @GET
    public List<Lanche> getListaLanche(){
        criarLanches();
        return listalanches;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes (MediaType.APPLICATION_JSON)
    public List<Lanche> setValores(List<Double> listaValores){
        valorAlface = listaValores.get(0);
        valorBacon = listaValores.get(1);
        valorHamburguer = listaValores.get(2);
        valorOvo = listaValores.get(3);
        valorQueijo = listaValores.get(4);
        criarLanches();
        return listalanches;
    }
    @POST
    @Path("precolanche")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
        public Lanche getPrecoLanche(Lanche lanche){
        boolean light=false;
        boolean temAlface = false;
        boolean temBacon=false;
        double valorLanche = 0;
        double precoIngrediente = 0;
 
        for (Ingredientes ingredienteTeste : lanche.getIngredientes()) {    
        if ((ingredienteTeste.getId() == 3 || ingredienteTeste.getId() == 5) && ingredienteTeste.getQtde() >= 3 ){
            Integer novaQtde = ingredienteTeste.getQtde()/ 3;
            novaQtde += novaQtde;
            ingredienteTeste.setQtde(novaQtde);
            light = true;
            }
            if (ingredienteTeste.getId() == 1) {
                temAlface = true;
            } else if(ingredienteTeste.getId() == 2) {
                temBacon = true;
            }
            precoIngrediente = (ingredienteTeste.getValor()) * ingredienteTeste.getQtde();
            valorLanche += precoIngrediente;
        }
        if(!light && (temAlface && !temBacon)) {
			valorLanche = valorLanche - (valorLanche * 0.10);
		}
        
        lanche.setTotal(valorLanche);
        return lanche;
    }
    
}
