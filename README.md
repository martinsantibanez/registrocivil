# Registro Civil
Aplicación realizada junto a Denisse Soto y Sebastián Ramírez durante el año 2016 como proyecto de la asignatura ICI 3241 / INF 2241.
CRUD en 2 niveles usando Java.

Información extraída del [informe](informe.pdf):
## Clases principales del sistema
-   **Registro Civil**: Es la clase más importante del sistema, y la que almacena toda la información del programa, por lo que es pasada a cada ventana. Contiene listas de regiones, extranjeros, empresas y administradores, y tiene la capacidad de realizar búsquedas y validaciones del sistema completo.
    
-   **Persona**: Es la clase abstracta que contiene los datos que todos los usuarios de nuestro sistema tienen en común. Contiene toda la información propia de una persona, tales como fecha de nacimiento, entre otros datos. La extienden las siguientes clases:
    

	- **Registrado**: Representa a una persona común y corriente registrada en el sistema. Un usuario de este tipo puede acceder al sistema y emitir sus propios certificados. Además puede ser de las clases adicionales extranjero o empresa.
    
	-   **Funcionario**: Puede emitir certificados de cualquier persona del Registro. También puede agregar, modificar y eliminar a los registrados, empresas y extranjeros, pero no a otros funcionarios.
    
	-   **Administrador**: Tiene acceso absoluto al sistema, pudiendo además de emitir certificados y modificar usuarios, agregar, modificar o eliminar sucursales.
    

-   **Lugar**: Es la interfaz para los “lugares” de nuestro sistema, los que comparten operaciones como buscar usuarios según su rut. La implementan las siguientes clases:
    

-   **Región**: Representa una región del país del Registro Civil. Contiene una lista de varias sucursales, además de los datos de la región.
    
-   **Sucursal**: Representa una oficina del Registro Civil. cada Registrado y cada Funcionario están asociados a una Sucursal, manteniendo esta listas de cada tipo de usuario, lo que incluye un ArrayList y un HashMap.
    

-   Otras clases importantes son las de lectura (**Lectura**) y escritura (**EscrituraExcel**) las cuales aprovechan las bondades del software de ofimática Excel para mantener un registro amigable de los datos manejados por el programa.
## Patrones de diseño

-   **Singleton**: Este patrón está diseñado para permitir solo una instancia de cierta clase, manteniéndola en la memoria para un uso futuro, por lo que nos resulta especialmente útil a la hora de instanciar las ventanas para luego ser reutilizadas, evitando así tener que volver a instanciar y crear datos que ya fueron cargados, incluso mejorando la velocidad de carga de las ventanas.
    
