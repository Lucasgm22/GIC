package symbols;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymbolTable {
	
	private Map<String, Identifier> symbols;
	
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

	public Map<String, Identifier> getSymbols() {
		return symbols;
	}

	public void setSymbols(Map<String, Identifier> symbols) {
		this.symbols = symbols;
	}

	public List<Identifier> getUnassignedIdentifiers() {
		return symbols.values().stream().filter(id -> !id.isAssigned()).toList();
	}
	

}
