package mcarb.viewmodel;

import mcarb.model.CalcModelFactory;

public class ViewModelFactory {

    private ViewModel viewModel;
    public ViewModelFactory(CalcModelFactory calcModelFactory) {
        viewModel = new ViewModel(calcModelFactory.getCalcModel());
    }

    public ViewModel getViewModel() {
        return viewModel;
    }
}
