package convertert.conversortapp.service;

import convertert.conversortapp.dto.RescisaoRequest;
import convertert.conversortapp.dto.RescisaoResponse;

public class TrabalhistaService {

    public static RescisaoResponse calcularRescisao(RescisaoRequest req) {
        double salario = req.getSalario();
        int dias = req.getDiasTrabalhados();
        int meses = req.getMesesTrabalhados();
        int anos = req.getAnosTrabalhados();
        boolean feriasVencidas = req.isFeriasVencidas();
        String tipo = req.getTipoRescisao();

        RescisaoResponse res = new RescisaoResponse();

        res.saldoSalario = calcularSaldoSalarial(salario, dias);
        res.feriasProporcionais = calcularFeriasProporcionais(salario, meses);
        res.tercoFerias = calcularTercoFerias(res.feriasProporcionais);
        res.decimoTerceiro = calcularDecimoTerceiro(salario, meses);

        res.feriasVencidas = feriasVencidas ? salario : 0;
        res.tercoFeriasVencidas = feriasVencidas ? calcularTercoFerias(salario) : 0;

        res.avisoPrevio = calcularAvisoPrevio(salario, tipo);
        res.multaFgts = calcularMultaFgts(salario, tipo);

        res.total = res.saldoSalario + res.feriasProporcionais + res.tercoFerias +
                res.decimoTerceiro + res.feriasVencidas + res.tercoFeriasVencidas +
                res.avisoPrevio + res.multaFgts;

        return res;
    }

    public static double calcularSaldoSalarial(double salario, int dias) {
        return (salario / 30) * dias;
    }

    public static double calcularFeriasProporcionais(double salario, int meses) {
        return (salario / 12) * meses;
    }

    public static double calcularTercoFerias(double ferias) {
        return ferias / 3;
    }

    public static double calcularDecimoTerceiro(double salario, int meses) {
        return (salario / 12) * meses;
    }

    public static double calcularAvisoPrevio(double salario, String tipo) {
        if ("sem_justa_causa".equalsIgnoreCase(tipo)) {
            return salario;
        }
        return 0;
    }

    public static double calcularMultaFgts(double salario, String tipo) {
        if ("sem_justa_causa".equalsIgnoreCase(tipo)) {
            return salario * 0.4;
        }
        return 0;
    }
}
