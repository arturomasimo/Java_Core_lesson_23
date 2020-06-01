package lession23;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

/**
 * The class to work with VR + iterators+list
 * @author ARTUR
 * @since JDK 13.0.2
 */
public class VRada {
	private static VRada vr13Convocation;
	List<Faction> factionVR13 = new ArrayList<Faction>();
	public static final String nonFractional = "NONFRACTIONAL";

	private VRada() {}

	public static VRada ifCreation() throws Exception {
		if (vr13Convocation == null) {
			vr13Convocation = new VRada();
			vr13Convocation.addFactionNonFractional();
		}
		return vr13Convocation;
	}

	public void addFactionNonFractional() throws Exception {
		factionVR13.add(new Faction(nonFractional));
	}

	@SuppressWarnings({ "resource" })
	public void addFaction() throws Exception {
		boolean bool = false;
		System.out.print("Введіть назву фракції ");
		String nameFaction = new Scanner(System.in).nextLine();

		Optional<Faction> findFirst = factionVR13.stream()
				.filter(temp -> temp.getNameFaction().equalsIgnoreCase(nameFaction)).findFirst();
		while (findFirst.isPresent() && (!nameFaction.equalsIgnoreCase(nonFractional))) {
			System.out.println("Помилка створення фрації " + nameFaction + " . Скоріше за все така фракція існує!");
			bool = true;
		}
		if (!bool) {
			factionVR13.add(new Faction(nameFaction));
			System.out.println("Фракція " + nameFaction + " успішно створена!");
		}
	}

	@SuppressWarnings("resource")
	public void removeFaction() throws Exception {
		boolean bool = false;
		System.out.print("Введіть назву фракції ");
		String nameFaction = new Scanner(System.in).nextLine();

		Optional<Faction> find = factionVR13.stream()
				.filter(temp -> temp.getNameFaction().equalsIgnoreCase(nameFaction)).findFirst();
		if ((find.isPresent()) && (nameFaction != nonFractional)) {
			System.out.println("фракція " + nameFaction + " видалена, всі депутати фракції переведені у позафракційні");
			for (Deputat deputat : find.get().deputaty) {
				addDeputatInFaction(nonFractional, deputat);
			}
			factionVR13.remove(find.get());
			bool = true;
		}
		if (!bool)
			System.out.println("фракція " + nameFaction + " НЕ знайдено!");
	}

	public void printAllFaction() {
		System.out.println("Зареєстровані такі фракції: ");
		factionVR13.forEach(f -> System.out.print(f.getNameFaction() + " "));
		System.out.println();
	}

	@SuppressWarnings("resource")
	public void removeAllDeputatInFaction() throws Exception {
		boolean bool = false;
		System.out.print("Введіть назву фракції ");
		String nameFaction = new Scanner(System.in).nextLine();
		Optional<Faction> find = factionVR13.stream()
				.filter(temp -> temp.getNameFaction().equalsIgnoreCase(nameFaction)).findFirst();

		if ((find.isPresent()) && (nameFaction != nonFractional)) {
			System.out
					.println("У фракція " + nameFaction + " очищена, всі депутатів фракції переведені у позафракційні");
			for (Deputat deputat : find.get().deputaty) {
				addDeputatInFaction(nonFractional, deputat);
			}
			find.get().removeAllDeputat();// remove(find.get());
			bool = true;
		}
		if (!bool)
			System.out.println("фракція " + nameFaction + " НЕ знайдено!");
	}

	@SuppressWarnings("resource")
	public String printAllDeputatInFaction() throws Exception {
		boolean bool = false;
		System.out.print("Введіть назву фракції ");
		String nameFaction = new Scanner(System.in).nextLine();

		Optional<Faction> find = factionVR13.stream()
				.filter(temp -> temp.getNameFaction().equalsIgnoreCase(nameFaction)).findFirst();
		if ((find.isPresent()) && (nameFaction != nonFractional)) {
			System.out.println("У фракції " + nameFaction + " є наступні депутати:");
			find.get().printAllDeputat();
			bool = true;
		}
		if (!bool) {
			System.out.println("фракція " + nameFaction + " НЕ знайдено!");
			return null;
		}
		return nameFaction;
	}

	public Deputat addRandomDeputat() throws Exception {
		Deputat deputat = new Deputat(0, 0);
		Random rand = new Random();
		String[] nameMass = { "Ivan", "Petro", "Oleg", "Stepan", "Mykola", "Maksum", "Stepan", "Danil" };
		String[] surNameMass = { "Ivanov", "Petrov", "Olegov", "Stepanov", "Matov", "Maksumov", "Stepanov", "Dumov" };
		deputat.setWeight(rand.nextInt(100) + 100);
		deputat.setHeigth(rand.nextInt(100) + 100);
		deputat.setAge(rand.nextInt(50) + 25);
		deputat.setXabar(true);
		deputat.setSurName(surNameMass[rand.nextInt(7)]);
		deputat.setName(nameMass[rand.nextInt(7)]);
		return deputat;
	}

	@SuppressWarnings("resource")
	public Deputat addDeputat() throws Exception {
		Deputat deputat = new Deputat(0, 0);
		System.out.print("Введіть імя ");
		deputat.setName(new Scanner(System.in).nextLine());
		System.out.print("Введіть прізвище ");
		deputat.setSurName(new Scanner(System.in).nextLine());
		System.out.print("Введіть вік ");
		deputat.setAge(Integer.parseInt(new Scanner(System.in).nextLine()));
		System.out.print("Введіть вагу ");
		deputat.setWeight(Integer.parseInt(new Scanner(System.in).nextLine()));
		System.out.print("Введіть зріст ");
		deputat.setHeigth(Integer.parseInt(new Scanner(System.in).nextLine()));

		System.out.print("Введіть чи хабарник: так (1), ні (0)");
		String ss = new Scanner(System.in).nextLine();
		if (ss.equals("1")) {
			deputat.setXabar(true);
		}
		if (ss.equals("0")) {
			deputat.setXabar(false);
		}
		return deputat;
	}

	@SuppressWarnings("resource")
	public void addDeputatInFaction(String nameFaction, Deputat deputat) throws Exception {
		boolean bool = false;
		Deputat d = new Deputat(0, 0);

		if (nameFaction == nonFractional) {
			Optional<Faction> find = factionVR13.stream()
					.filter(temp -> temp.getNameFaction().equalsIgnoreCase(nonFractional)).findFirst();
			if (find.isPresent())
				find.get().addDeputat(deputat);
			bool = true;
		} else {
			System.out.print("Введіть назву фракції ");
			String nameFaction1 = new Scanner(System.in).nextLine();
			System.out.print("Будемо вводити руками (1) чи згенеруємо рандомно (2)?");
			switch (new Scanner(System.in).nextLine()) {
				case "1": {	d = addDeputat();}
				case "2": {	d = addRandomDeputat();}
			}
			if (d.isXabar()) {
				System.out.println(
						"Депутату " + d.getName() + " " + d.getSurName() + " потрібно дати хабар, введіть число");
				int xabarNew = Integer.parseInt(new Scanner(System.in).nextLine());
				d.giveXabar(xabarNew);
			}

			Optional<Faction> find = factionVR13.stream()
					.filter(temp -> temp.getNameFaction().equalsIgnoreCase(nameFaction1)).findFirst();
			if (find.isPresent()) {
				System.out.println("Додано депутата у фракцію");
				find.get().addDeputat(d);
				bool = true;
			}
		}
		if (!bool)
			System.out.println("Помилка додавання депутата у фракцію");
	}

	@SuppressWarnings("resource")
	public void removeDeputatFromFaction(String nameFaction) throws Exception {
		boolean bool = false;
		System.out.print("Введіть Імя та Прізвище депутата, якого видалити (через пробіл) :");
		String st1 = new Scanner(System.in).nextLine();
		String[] s = st1.split(" ");
		String name = s[0], surName = s[1];

		Optional<Faction> find = factionVR13.stream()
				.filter(temp -> temp.getNameFaction().equalsIgnoreCase(nameFaction)).findFirst();
		if (find.isPresent()) {
			for (Deputat deputat : find.get().deputaty) {
				if ((deputat.getName() == name) && (deputat.getSurName() == surName)) {
					addDeputatInFaction(nonFractional, deputat);
					find.get().removeDeputat(name, surName);// deputat
					System.out.println("З фракції " + nameFaction + " видалили депутата " + name + " " + surName
							+ " і перевели в позафракційні");
					bool = true;
				}
			}
		}
		if (!bool)
			System.out.println("Помилка видалення депутата " + name + " " + surName);

	}

	@SuppressWarnings("resource")
	public void printAllDeputatXabarnuk() throws Exception {
		System.out.print("Введіть назву фракції ");
		String nameFaction = new Scanner(System.in).nextLine();
		Optional<Faction> find = factionVR13.stream()
				.filter(temp -> temp.getNameFaction().equalsIgnoreCase(nameFaction)).findFirst();
		if (find.isPresent()) {
			find.get().printAllDeputatXabarnuk();
		}
	}

	@SuppressWarnings("resource")
	public void printBiggestDeputatXabarnukInFraction() throws Exception {
		System.out.print("Введіть назву фракції ");
		String nameFaction = new Scanner(System.in).nextLine();
		Optional<Faction> find = factionVR13.stream()
				.filter(temp -> temp.getNameFaction().equalsIgnoreCase(nameFaction)).findFirst();
		if (find.isPresent()) {
			System.out.println(find.get().findBiggestDeputatXabarnuk());// printAllDeputatXabarnuk();
		}
	}

}
