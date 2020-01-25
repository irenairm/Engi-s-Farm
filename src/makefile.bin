JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
        $(JC) $(JFLAGS) $*.java

CLASSES = \
		Product.java \
		FarmProduct.java \
		TelurAngsa.java \
		TelurAyam.java \
		TelurBebek.java \
		DagingAngsa.java \
		DagingAyam.java \
		DagingBebek.java \
		DagingDomba.java \
		DagingKuda.java \
		DagingSapi.java \
		SusuDomba.java \
		SusuKuda.java \
		SusuSapi.java \
		Except.java \
        Renderable.java \
        Cell.java \
        Land.java \
        Barn.java \
        Coop.java \
        GrassLand.java \
        Facility.java \
        Mixer.java \
        Truck.java \
        Well.java \
        LivingCreature.java \
        FarmAnimal.java \
        EggProdAnimal.java \
        MeatProdAnimal.java \
        MilkProdAnimal.java \
        Angsa.java \
        Ayam.java \
        Bebek.java \
        Domba.java \
        Kuda.java \
        Sapi.java \
        Player.java \
        State.java \
        Main.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
        $(RM) *.class