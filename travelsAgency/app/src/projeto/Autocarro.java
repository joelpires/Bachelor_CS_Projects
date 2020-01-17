
package projeto;

import java.io.Serializable;

/**
 *
 * @author Francisco Fernandes e Joel Pires
 */
public final class Autocarro implements Serializable{
    private String matricula;
    private int lugares;            //DIZ RESPEITO AO NUMERO DO LUGAR ATRIBUIDO AQUANDO DE UMA RESERVA
    private int lotacao;

    public Autocarro(){}
    public Autocarro(String m, int l){
        matricula=m;
        lotacao=l;
        lugares=l;
    }
    
    /**
     * GET MATRICULA DO AUTOCARRO
     * @return matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * GET LOTAÇÃO DO AUTOCARRO
     * @return lotacao
     */
    public int getLotacao() {
        return lotacao;
    }

    /**
     * SET LOTAÇÃO DO AUTOCARRO
     * @param lotacao
     */
    public void setLotacao(int lotacao) {
        this.lotacao = lotacao;
    }

    /**
     * SET MATRICULA DO AUTOCARRO
     * @param matricula
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * GET NÚMERO DO LUGAR DO PRIMEIRO CLIENTE A USAR O AUTOCARRO
     * @return lugares
     */
    public int getLugares() {
        return lugares;
    }

    /**
     * SET NÚMERO DO LUGAR DO PRIMEIRO CLIENTE A USAR O AUTOCARRO
     * @param lugares
     */
    public void setLugares(int lugares) {
        this.lugares = lugares;
    }
    
    /**
     * MÉTODO toString()
     * @return String
     */
    @Override
    public String toString(){
        return "\n\tMatrícula:" + this.matricula + "\t" + "Lotação:" + this.lotacao;
    }
    
    
}
