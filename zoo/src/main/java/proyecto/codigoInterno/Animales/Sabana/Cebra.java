package proyecto.codigoInterno.Animales.Sabana;

import java.util.concurrent.*;
import javax.swing.ImageIcon;

import proyecto.codigoInterno.Alimento.Herbivoro;
import proyecto.codigoInterno.Animales.Animal;
import proyecto.codigoInterno.Habitats.Habitat;

public class Cebra extends Animal implements Herbivoro{
    private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> futureTask;
    private boolean come;
    private Habitat habitat;
    

    public Cebra(String nombre){
        super(nombre);
        this.come = false;
        imagenIcon = new ImageIcon(getClass().getResource("/animales/Sabana/cebra.png"));
    }
    public String getEspecie(){
        return "Cebra";
    }
    public String getHabitat(){
        return "Sabana";
    }
   
    @Override
    public boolean comerPlanta(boolean come) {
        this.come = come;
        return come;
    }
    @Override
    public String pedirPlanta() {
        if(comerPlanta(come)){
            futureTask.cancel(true);
        }
        futureTask = scheduler.schedule(() -> {
            
        desaparecer(habitat);
        }, 5, TimeUnit.MINUTES);
        return "Necesita Plantas...";
    }
    @Override
    public void pedirComida(Habitat habitat) {
    this.habitat = habitat;
       pedirPlanta();
    }
    
    @Override
    public void comer() {
        comerPlanta(come);
        pedirComida(habitat);
        come = false;
    }
}
