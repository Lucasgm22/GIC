package symbols;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SymbolTable {
	
	private HashMap<String, Identifier> symbols;
	
	public SymbolTable() {
		this.symbols = new HashMap<>();
	}	
	
	public Identifier get(String key) {
		return this.symbols.get(key);
	}
	
	public void add(String key, Identifier id) {
		this.symbols.put(key, id);
	}
	
	public boolean exists(String key) {
		return this.symbols.containsKey(key);
	}

	public HashMap<String, Identifier> getSymbols() {
		return symbols;
	}

	public void setSymbols(HashMap<String, Identifier> symbols) {
		this.symbols = symbols;
	}

	public List<Identifier> getUnassignedIdentifiers() {
		return symbols.values().stream().filter(id -> !id.isAssigned()).toList();
	}
	

}
