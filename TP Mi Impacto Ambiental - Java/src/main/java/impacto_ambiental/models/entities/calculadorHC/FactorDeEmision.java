package impacto_ambiental.models.entities.calculadorHC;

import impacto_ambiental.models.entities.EntidadPersistente;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "factores_de_emision")
public class FactorDeEmision extends EntidadPersistente {
  @Transient
  private CalculadorDeHC calculadorDeHC;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipoActividad")
  private TipoActividadDA tipoActividad;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipoConsumo")
  private TipoConsumoDA tipoConsumo;
  @Column(name = "factor")
  private double factorEmision;


  public FactorDeEmision(TipoActividadDA tipoDeActividad, TipoConsumoDA tipoDeConsumo, double factorEmision) {
    this.tipoActividad = tipoDeActividad;
    this.tipoConsumo = tipoDeConsumo;
    this.factorEmision = factorEmision;
    this.calculadorDeHC = CalculadorDeHC.getInstance();
  }

  public FactorDeEmision() {
    this.calculadorDeHC = CalculadorDeHC.getInstance();
  }

  public TipoActividadDA getTipoActividad() {
    return tipoActividad;
  }

  public TipoConsumoDA getTipoConsumo() {
    return tipoConsumo;
  }

  public double getFactorEmision() {
    return factorEmision;
  }

  public void setFactorEmision(double nuevoFactor){
    this.factorEmision = nuevoFactor;
  }

}

