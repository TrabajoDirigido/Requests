package requests;

import interfaces.LogicRequest;

import java.util.List;

import variables.ResSet;

public class OrRequest extends LogicRequest{

	public OrRequest(ResSet input) {
		super(input);
	}

	@Override
	public boolean reduce(List<Boolean> res) {
		boolean r = false;
		for(Boolean b : res){
			r = b||r;
		}
		return r;
	}

}
