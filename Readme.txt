Acest proiect implementeaza un Catalog care poate fi folosit in mediul Universitar.
In cadrul acestui proiect am pus in aplicare concepte ale programarii orientate pe
obiecte cum ar fi mostenirea, incapsulare, genericitate, polimorfism si abstractizare.
Pe langa acestea in acest proiect am folosit diferite desing patternuri, cum ar fi
Singleton in cadrul clasei catalog, Factory pentru clasa UserFactory, Builder
pentru clasa Course si alte patternuri.
Pentru rulare se da Run clasei Test.
Pentru a crea un student, parinte, profesor sau asistent se creeaza un user
cu ajutorul metodei createUser al clasei UserFactory. Ea primeste ca parametri
tipul, prenumele si numele.
O grupa se creeaza cu ajutorul unui Id si unui Asistent, iar un curs se creeaza cu
ajutorul unui Profesor de curs, un nume, si unei ierarhii in functie de nota finala,
nota de pe parcurs sau nota din examen.
Ulterior adaugam studentii in grupe, iar grupele si studentii in diferite cursuri.
Pentru ca un student sa fie adaugat intr-un curs, el trebuie sa aiba si id-un unei grupe.
In acest catalog se creeaza notele care corespund unui singur curs, cu ajutorul unei note
de pe parcurs si una din examenul final. Dupa care fiecare nota ii este atribuita unui
student, daca mai multi studenti au aceeasi nota nu mai este nevoie sa creem alta, astfel
putem economisi memorie.
Daca se doreste parintii pot fi anuntati despre o nota, apeland metoda notifyObservers.
