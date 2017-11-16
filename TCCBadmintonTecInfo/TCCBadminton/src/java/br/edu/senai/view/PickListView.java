package br.edu.senai.view;

import br.edu.senai.controller.AlunoDAO;
import br.edu.senai.model.Aluno;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DualListModel;
import org.springframework.security.core.context.SecurityContextHolder;

@ManagedBean
public class PickListView {

    private DualListModel<Aluno> alunos;

    
    public void atualizarTarget(List<Aluno> alunosExistentes){
        this.alunosTarget = alunosExistentes; 
        alunos.setTarget(alunosTarget);
        for (int i = 0; i < alunosTarget.size(); i++) {
                alunosSource.remove(alunosTarget.get(i));
        }
        alunos.setSource(alunosSource);
    }
    public DualListModel<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(DualListModel<Aluno> alunos) {
        this.alunos = alunos;
    }

    public List<Aluno> getAlunosSelecionados() {

        List<Aluno> alunosSelecionados = new ArrayList<>();
        List<Aluno> listaTarget = getAlunos().getTarget();

       // for (Aluno alunoEscolhido : listaTarget) {
        for(int x = 0; x < listaTarget.size();x++) {
 
            alunosSelecionados.add(listaTarget.get(x));
        }
    
        return alunosSelecionados;
    }

    List<Aluno> alunosSource = new ArrayList<>();
    List<Aluno> alunosTarget = new ArrayList<>();

    @PostConstruct
    public void init() {
        //Cities
        AlunoDAO alunoDAO = new AlunoDAO();
        alunosSource = alunoDAO.listar(SecurityContextHolder.getContext().getAuthentication().getName());
        alunos = new DualListModel<Aluno>(alunosSource, alunosTarget);

    }
}
