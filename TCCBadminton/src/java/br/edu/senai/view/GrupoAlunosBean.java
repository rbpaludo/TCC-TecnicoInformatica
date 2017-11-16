package br.edu.senai.view;

import br.edu.senai.controller.GrupoAlunosDAO;
import br.edu.senai.model.Aluno;
import br.edu.senai.model.Exercicio;
import br.edu.senai.model.GrupoAlunos;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.security.core.context.SecurityContextHolder;

@ManagedBean

public class GrupoAlunosBean {

    private GrupoAlunos grupoAlunos = new GrupoAlunos();
    GrupoAlunosDAO grupoDAO = new GrupoAlunosDAO();
    private List<Aluno> alunosAntesAtualizar = new ArrayList<>();
    private List<Exercicio> filtroGrupo;

    public List<Exercicio> getFiltroGrupo() {
        return filtroGrupo;
    }

    public void setFiltroGrupo(List<Exercicio> filtroGrupo) {
        this.filtroGrupo = filtroGrupo;
    }

    public void salvar(PickListView pickListView, String treinador) {

        this.grupoAlunos.setlAlunos(pickListView.getAlunosSelecionados());

        if (grupoAlunos.getId() == null) {
            grupoDAO.salvarAdicionar(grupoAlunos, treinador);
        } else {
            grupoDAO.salvarAlterar(grupoAlunos, alunosAntesAtualizar);
        }
        pickListView.init();
        this.grupoAlunos = new GrupoAlunos();
    }

    public void excluir(GrupoAlunos grupoExcluir, PickListView pickListView) {
        grupoDAO.excluir(grupoExcluir);
        grupoAlunos = new GrupoAlunos();
        pickListView.init();
    }

    public GrupoAlunos getGrupoAlunos() {
        return grupoAlunos;
    }

    public List<GrupoAlunos> listarGrupos() {
        return grupoDAO.listar(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public List<GrupoAlunos> listarGruposDoTreinador(String treinador) {
        return grupoDAO.listarDoTreinador(treinador);
    }

    public void setGrupoAlunos(GrupoAlunos grupoAlunos, PickListView pickListView) {
        this.grupoAlunos = grupoAlunos;
        pickListView.atualizarTarget(grupoAlunos.getlAlunos());
        alunosAntesAtualizar = grupoAlunos.getlAlunos();
    }
}
