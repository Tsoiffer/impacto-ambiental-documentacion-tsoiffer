package impacto_ambiental.models.entities.perfil;

import impacto_ambiental.models.entities.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Entity
@Table(name = "areas")
public class Area extends EntidadPersistente {
    @Getter
    @Setter
    @Column(name = "nombre")
    public String nombre;

    @ManyToOne()
    @JoinColumn(name = "organizaciones_id", referencedColumnName = "id")
    private Organizacion organizacion;
    /*
    @ManyToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    private List<Miembro> miembros = new ArrayList<>();
    @Transient
    private List<Miembro> miembrosPendientes = new ArrayList<>();
*/
    @Getter
    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY,mappedBy = "area")
    private  List<Solicitud> solicitudes = new ArrayList<>();

    public Area(String nombreArea, Organizacion nombreOrganizacion) {
        this.nombre = nombreArea;
        this.organizacion = nombreOrganizacion;
    }

    public Area() {

    }

    public Organizacion getOrganizacion() {
        return this.organizacion;
    }

    public void gestionarMiembrosPendientes(Solicitud solicitud, SolicitudEstado estado) {
        solicitud.setEstado(estado);
    }
    public void desvincularMiembro(Miembro unMiembro) {
        Optional<Solicitud> laSolicitud = this.solicitudes.stream().filter(unaSolicitud -> unaSolicitud.getMiembro() == unMiembro && unaSolicitud.getEstado() == SolicitudEstado.ACEPTADA ).findFirst();
        if(laSolicitud.isPresent()) {
            laSolicitud.get().setEstado(SolicitudEstado.DESVINCULADO);
        }
    }

    public void agregarAMiembroPendiente(Solicitud solicitud){

        this.solicitudes.add(solicitud);
    }

    public double calcularHCporMiembro(Integer anio,Integer mes){
//        return this.calcularHC(anio,mes)/this.miembrosActuales().size();
//        TODO
        return 0.0;
    }

    private List<Solicitud> miembrosActuales(){
        return this.solicitudes.stream().filter(solicitud -> solicitud.getEstado() == SolicitudEstado.ACEPTADA).collect(Collectors.toList());
    }
    public void calcularHC(){
       solicitudes.stream().forEach(e->e.calcularHC());
    }

}
