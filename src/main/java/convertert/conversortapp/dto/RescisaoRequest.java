package convertert.conversortapp.dto;

public class RescisaoRequest {
    private String tipoRescisao;
    private double salario;
    private int diasTrabalhados;
    private int mesesTrabalhados;
    private boolean feriasVencidas;
    private int anosTrabalhados;

    // Getters e Setters
    public String getTipoRescisao() {
        return tipoRescisao;
    }

    public void setTipoRescisao(String tipoRescisao) {
        this.tipoRescisao = tipoRescisao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getDiasTrabalhados() {
        return diasTrabalhados;
    }

    public void setDiasTrabalhados(int diasTrabalhados) {
        this.diasTrabalhados = diasTrabalhados;
    }

    public int getMesesTrabalhados() {
        return mesesTrabalhados;
    }

    public void setMesesTrabalhados(int mesesTrabalhados) {
        this.mesesTrabalhados = mesesTrabalhados;
    }

    public boolean isFeriasVencidas() {
        return feriasVencidas;
    }

    public void setFeriasVencidas(boolean feriasVencidas) {
        this.feriasVencidas = feriasVencidas;
    }

    public int getAnosTrabalhados() {
        return anosTrabalhados;
    }

    public void setAnosTrabalhados(int anosTrabalhados) {
        this.anosTrabalhados = anosTrabalhados;
    }
}
