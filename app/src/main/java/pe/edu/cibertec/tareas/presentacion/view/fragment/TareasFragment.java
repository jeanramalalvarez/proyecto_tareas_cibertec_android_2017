package pe.edu.cibertec.tareas.presentacion.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.Unbinder;
import pe.edu.cibertec.tareas.R;
import pe.edu.cibertec.tareas.presentacion.model.TareaModel;
import pe.edu.cibertec.tareas.presentacion.presenter.TareasPresenter;
import pe.edu.cibertec.tareas.presentacion.view.view.TareasView;


public class TareasFragment extends Fragment implements TareasView {

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.list_tareas)
    ListView listView;

    @BindView(R.id.fab_agregar)
    FloatingActionButton fabAgregar;

    private ArrayAdapter<TareaModel> adapter;
    private List<TareaModel> tareaList = new ArrayList<>();

    private TareasPresenter tareasPresenter;

    private Unbinder unbinder;

    private OnTareaClickListener onNoticiaClickListener;

    private int index = 0;

    public TareasFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            onNoticiaClickListener = (OnTareaClickListener) context;
        }catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " debe implementar OnTareaClickListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tareas, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);

        if (savedInstanceState != null) {
            index = savedInstanceState.getInt("index", 0);
        }

        adapter = new ArrayAdapter<TareaModel>(
                getContext(),
                android.R.layout.simple_list_item_activated_1,
                tareaList);
        listView.setAdapter(adapter);

        tareasPresenter = new TareasPresenter(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //tareasPresenter.listarTareas();
        getActivity().setTitle("Mis Tareas");
    }

    @Override
    public void onStart() {
        super.onStart();
        tareasPresenter.listarTareas();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("index", index);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void mostrarLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public Context context() {
        return getContext();
    }

    @OnItemClick(R.id.list_tareas)
    public void onItemClick(int position) {
        index = position;
        listView.setItemChecked(position, true);

        verTarea(tareaList.get(position));
    }

    @OnClick(R.id.fab_agregar)
    public void onAgregarClick() {
        agregarTarea();
    }

    @Override
    public void listarTareas(List<TareaModel> tareaList) {
        adapter.clear();
        adapter.addAll(tareaList);

        if (getResources().getBoolean(R.bool.dual_pane)) {
            listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

            listView.setItemChecked(index, true);

            verTarea(tareaList.get(index));
        }
    }

    @Override
    public void verTarea(TareaModel tarea) {
        onNoticiaClickListener.onTareaClick(tarea);
    }

    @Override
    public void agregarTarea() {
        onNoticiaClickListener.onAgregarTareaClick();
    }

    public interface OnTareaClickListener {
        void onTareaClick(TareaModel tarea);
        void onAgregarTareaClick();
    }
}
