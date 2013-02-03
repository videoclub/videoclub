mai1226 - 15/12/2012 - 8:00
Ekana neo aspect branch, sto opoio eprepe na kano new AspectJ Project(einai ipoxrewtiko gia ti xrisi aspects).
Prin mpeite sti diadikasia na kanete pull klp, kalitera katebaste prota to ajdt plugin gia to eclipse.
H diadikasia exei os eksis:
1. Check ti version tou eclipse(sti periptosi mas exoume to eclipse indigo sr2 'h allios eclipse 3.7.2).
2. Exontas upopsin to parapano, pigainoume sti selida http://eclipse.org/ajdt/
3. Klik aristera sta downloads.
4. Pigainoume sto dev builds for eclipse 3.7 kai pernoume me copy to link pou exei: http://download.eclipse.org/tools/ajdt/37/dev/update
5. Anoigoume to eclipse kai pigainoume Help > Install New Software.. > Add..
6. Dinoume ena name (p.x ajdt) kai apo kato sto Location: paste to url pou phrame prohgoumenws me copy.
7. Dialegoume to AspectJ Development Tools (Required) kai meta Next.
8. Opou zitisei na kanete accept(se policies) kai restart(to eclipse), kante to.


mai1226 - 15/12/2012 - 8:15
Prosthiki external jar(objectdb.jar) sto build path tou project me ti gnosti diadikasia.
H synthiki episis leei pos oti external libraries einai na xrisimopoihsoume se ena project,
prepei na ta bazoume prota se ena fakelo lib (dld ..\videoclub\lib\blabla.jar)
kai apo ekei na ta kanoume browse apo to build path kai add.


mai1226 - 24/12/2012 - 15:00
Ylopoihsh Product - Entity - DAO(+GenericDao)
Mesa apo ti main() tou Login.java, mporeite na treksete ena aplo console paradeigma.


mai1226 - 07/01/2013 - 19:00
1. Diorthoseis se Dao/DaoImpl, ProductDao/ProductDaoImpl.
2. Prosthiki twn ypoloipwn model.entities me basi to branch-Model sto github.
3. Afairesi twn aspect/login paketwn gia pio sosta apotelesmata stis metrikes kai
   gia na sygxronizetai to sigkekrimeno branch me to LK branch.
4. Mikres tropopoihseis sto model.User (added @Index -> username, added empty constructor,
   removed toString()-override method).
5. Prosthiki UserDao/UserDaoImpl.


mai1226 - 08/01/2013 - 23.00
Prosthiki PersistenceController gia ti diaxeirisi twn connections
kai genika setarisma stis ypoloipes klasseis


mai1226 - 08/01/2013 - 23.00
Prosthiki PersistenceController gia ti diaxeirisi twn connections
kai genika setarisma stis ypoloipes klasseis


mai1226 - 31/01/2013 - 06.00
Updates se model - dao - controller - view


mai1226 - 31/01/2013 - 08.00
Prosthiki model.SystemLogger , dao.SystemLoggerDao , dao.impl.SystemLoggerDaoImpl, sysaspect.SystemLog
gia katagrafi twn kinisewn os pros tin efarmogi(system log).