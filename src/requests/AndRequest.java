package requests;

import interfaces.LogicRequest;

import java.util.List;

import variables.ResSet;

public class AndRequest extends LogicRequest{

	public AndRequest(ResSet input) {
		super(input);
	}

	@Override
	public boolean reduce(List<Boolean> res) {
		boolean r = true;
		for(Boolean b : res){
			r = b&&r;
		}
		return r;
	}

}
