package impacto_ambiental.models.entities.reportes;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ReporteHistorico {
    @Getter
    List<ReporteMensual> reportesMensuales= new ArrayList<ReporteMensual>();

    public void agregarHc(HChistorico hc){
         Double huella = hc.getHuellaDeCarbono();
         Integer anio = hc.getAnio();
         Periodo periodo = hc.getPeriodo();

         if(periodo==Periodo.Anual){

            for (int i = 1; i<=12;i++){

                this.reportar(Periodo.getPeriodo(i),anio,huella/12);
             }
             return;
         }
        this.reportar(periodo,anio,huella);
         return;
    }

    private void reportar(Periodo periodo, Integer anio, Double hc){
        ReporteMensual reporte = reportesMensuales.stream().filter(e->e.getAnio().equals(anio)).filter(e->e.getPeriodo().equals(periodo)).findAny().orElse(nuevoReporteMensual(periodo,anio));
        reporte.sumar(hc);
    }

    private ReporteMensual nuevoReporteMensual(Periodo periodo, Integer anio){
        ReporteMensual reporte = new ReporteMensual(periodo,anio);
        reportesMensuales.add(reporte);
        return reporte;
    }



    /*
    @Getter
    List<Double> factoresDeEmision=new ArrayList<>();
    @Getter
    Periodo mesInicio;
    @Getter
    Periodo mesFin;
    @Getter
    Integer anioInicio;
    @Getter
    Integer anioFin;
    public ReporteHistorico(Periodo mesInicio,Integer anioInicio,Periodo mesFin,Integer anioFin){
        this.mesInicio=mesInicio;
        this.mesFin=mesFin;
        this.anioInicio=anioInicio;
        this.anioFin=anioFin;
        Integer cantMeses = (anioFin-anioInicio)*12+(Periodo.toInteger(mesFin)-Periodo.toInteger(mesInicio));
        for (int i=0;i<cantMeses;i++){
            factoresDeEmision.add(0.0);
        }

    }
    public void agregarFactorDeEmision(Double factorDeEmision,Integer anio,Periodo mes){
        Integer cantMeses = (anio-anioInicio)*12+(Periodo.toInteger(mes)-Periodo.toInteger(mesInicio));
        Double sum = factoresDeEmision.get(cantMeses)+factorDeEmision;
        factoresDeEmision.set(cantMeses,sum);
    }
    public void agregarAnual(Integer anio,Double factorDeEmision){
        Integer cantMeses = (anio-anioInicio)*12;
        for (int i=0;i<12;i++){
            Double sum = factoresDeEmision.get(cantMeses+i)+factorDeEmision/12;
            factoresDeEmision.set(cantMeses+i,sum);
        }
    }
*/
    }
