package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Meta {
	
	@Id
    @GeneratedValue
    private Long id;

    private String nome, descricao, prioridade, data;
    private boolean status;

    public Meta(String nome, String descricao, String data, String prioridade) {
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.prioridade = prioridade;
        this.status = false;
    }

    public Meta() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public boolean setStatus() {
		return this.status = true;
	}
	
	public String isConcluida() {
		if (status) {
			return "Concluida";
		}
		
		return "Não concluida";		
	}
	
	public int getSemana(){
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		
		Calendar data = Calendar.getInstance();
		try {
			data.setTime(formatter.parse(getData()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return data.get(Calendar.WEEK_OF_YEAR);
	}

}
