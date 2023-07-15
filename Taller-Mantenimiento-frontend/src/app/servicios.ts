export class Servicios {

id: number;
nombre: string; // Nombre de la oferta de trabajo.
descripcion: string; // Descripcion corta de la oferta de trabajo
fecha: Date; // Fecha de publicacion de la oferta del trabajo.
salario: number; // Precio del servicio.
estatus: string; // Valores ["Creado", "Aprobado", "Eliminado"].
destacado: number ; // Valores [0, 1]. 0: No se muestra en la pag. principal | 1: Se muestra en la pagina principal.
imagen: string = "no-image.png"; // Nombre del archivo de la imagen del logotipo de la empresa que ofrece el trabajo.
detalles: string; // Detalles de la oferta de trabajo.
idCategoria: number = 4;
}
