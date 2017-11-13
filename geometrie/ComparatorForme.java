package geometrie;

import java.util.Comparator;

public interface ComparatorForme {
	static final Comparator<Forme> AIRE_ORDER = 
			new Comparator<Forme>()
			{

				@Override
				public int compare(Forme f1, Forme f2) {
					double t1 = f1.calculAire();
					double t2 = f2.calculAire();
					if(t1 < t2)
					{
						return 1;
					}
					else if(t1 == t2)
					{
						return f1.getName().compareTo(f2.getName());
					}
					else
					{
						return -1;
					}
				}
		
			};
	static final Comparator<Forme> PERIMETRE_ORDER =
			new Comparator<Forme>()
			{

				@Override
				public int compare(Forme f1, Forme f2) {
					double t1 = f1.calculPerimetre();
					double t2 = f2.calculPerimetre();
					if(t1 < t2)
					{
						return 1;
					}
					else if(t1 == t2)
					{
						return f1.getName().compareTo(f2.getName());
					}
					else
					{
						return -1;
					}
				}
		
			};
	static final Comparator<Forme> DIST_ORIGIN_ORDER =
			new Comparator<Forme>()
			{

				@Override
				public int compare(Forme f1, Forme f2) {

					double t1 = f1.distanceOrigine();
					double t2 = f2.distanceOrigine();
					if(t1 < t2)
					{
						return 1;
					}
					else if(t1 == t2)
					{
						return 0;
					}
					else
					{
						return -1;
					}
				}
				
			};
	static final Comparator<Forme> NAME_ORDER = 
			new Comparator<Forme>()
			{
				@Override
				public int compare(Forme f1, Forme f2) {
					return f1.getName().compareTo(f2.getName());
				}
			};
}
