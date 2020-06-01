package lession23;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The class to work factions objects
 * @author ARTUR
 * @since JDK 13.0.2
 */
public class Faction {
	private String nameFaction;
	List<Deputat> deputaty = new ArrayList<Deputat>();

	public Faction(String nameFaction) {
		super();
		this.nameFaction = nameFaction;
	}

	public void addDeputat(Deputat deputat) {
		deputaty.add(deputat);
	}

	public boolean removeDeputat(String name, String surName) {
		boolean bool = false;
		Optional<Deputat> find = deputaty.stream()
				.filter(n -> n.getSurName().equals(surName) && n.getName().equals(name)).findFirst();
		if (find.isPresent()) {
			deputaty.remove(find.get());
			bool = true;
		}
		return bool;
	}

	public Deputat returnDeputat(String name, String surName) {
		Optional<Deputat> find = deputaty.stream()
				.filter(n -> n.getSurName().equals(surName) && n.getName().equals(name)).findFirst();
		if (find.isPresent())
			return find.get();
		return null;
	}

	public void printAllDeputat() {
		deputaty.forEach(System.out::println);
	}

	public void printAllDeputatXabarnuk() {
		List<Deputat> isXabar = deputaty.stream().filter(Deputat::isXabar).collect(Collectors.toList());
		isXabar.forEach(System.out::println);
	}

	public Deputat findBiggestDeputatXabarnuk() {
		int maxXabar = 0;
		Deputat maxDeputatXabarnuk = null;

		Iterator<Deputat> d = deputaty.listIterator();
		while (d.hasNext()) {
			Deputat temp = d.next();
			if (temp.isXabar() == true) {
				if (temp.getXabarSize() > maxXabar) {
					maxXabar = temp.getXabarSize();
					maxDeputatXabarnuk = temp;
				}
			}
		}
		return maxDeputatXabarnuk;
	}

	public void removeAllDeputat() {
		deputaty.clear();
	}

	public String getNameFaction() {
		return nameFaction;
	}

	public void setNameFaction(String nameFaction) {
		this.nameFaction = nameFaction;
	}

	public List<Deputat> getDeputaty() {
		return deputaty;
	}

	@Override
	public String toString() {
		return "Faction [nameFaction=" + nameFaction + ", deputaty=" + deputaty + "]";
	}

}
