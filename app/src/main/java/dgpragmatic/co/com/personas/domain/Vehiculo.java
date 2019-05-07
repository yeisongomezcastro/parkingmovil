package dgpragmatic.co.com.personas.domain;

import android.text.Editable;

public class Vehiculo {
    private String placa;
    private Integer cilindraje;
    private String tipoVehiculo;

    public Vehiculo(String placa, Integer cilindraje, String tipoVehiculo) {
        this.placa = placa;
        this.cilindraje = cilindraje;
        this.tipoVehiculo = tipoVehiculo;
    }
    public Vehiculo(){}

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(Integer cilindraje) {
        this.cilindraje = cilindraje;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }
}

