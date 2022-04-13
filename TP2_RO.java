import ilog.concert.*;
import ilog.cplex.*;
public class exoTP {
public static void main(String[] args) {
calcul ();
  }

    public static void calcul (){
    try {
IloCplex simplexe = new IloCplex ();
// declaration des Variables de decision de type reel
IloNumVar var_decis [][] = new IloNumVar[
[0,15,37,55,24,60,18,33,48,40,58,67],
[15,0,22,40,38,52,33,48,42,55,61,61],
[37,22,0,18,16,30,41,28,20,58,39,39],
[55,40,18,0,34,12,59,46,24,62,43,34],
[24,38,16,34,0,36,25,12,24,47,37,43],
[60,52,30,12,36,0,57,42,12,50,31,22],
[18,33,41,59,25,57,0,15,45,22,40,61],
[33,48,28,46,12,42,15,0,30,37,25,46],
[48,42,20,24,24,12,45,30,0,38,19,19],
[40,55,58,62,47,50,22,37,38,0,19,40],
[58,61,39,43,37,31,40,25,19,19,0,21],
[67,61,39,34,43,22,61,46,19,40,21,0]];
for (int i=0;i<2;i++){
var_decis[i][0]= simplexe.numVar(0, Double.MAX_VALUE);
}//declaration de la fonction objectif

IloLinearNumExpr objectif = simplexe.linearNumExpr();
// Definition des coefficients de la fonction objectif
objectif.addTerm(8, var_decis[0][0]);
// Definir le type d'otimisation de la fonction (max ou min )
simplexe.addMinimize(objectif);
// contrainte 1 : sum X[i][j]<= 1
IloLinearNumExpr contrainte_1 = simplexe.linearNumExpr();
contrainte_1.addTerm(1, var_decis[0][0]);
contrainte_1.addTerm(2, var_decis[1][0]);
simplexe.addLe(contrainte_1, 8);
// la meme chose pour les autres contraintes
simplexe.solve(); // lancer la resolution
// Afficher des resultat
System.out.println("Voici la valeur de la fonction objectif "+
simplexe.getObjValue());
System.out.println(" Voici les valeurs des variables de decision: ") ;
for (int i=0;i<2;i++)
System.out.println( "X"+i+ " = "+ simplexe.getValue(var_decis[i][0]));
} catch (IloException e){
System.out.print("Exception levee " + e);
}
    }
}