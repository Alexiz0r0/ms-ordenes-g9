package com.jaob.ms_ordenes.aggregates.constants;

public class Constantes {
    // Mensajes de éxito
    public static final String MESSAGE_SUCCESSFUL = "Operación realizada con éxito.";
    public static final String MESSAGE_CREATED = "Recurso creado correctamente.";
    public static final String MESSAGE_UPDATED = "Recurso actualizado correctamente.";
    public static final String MESSAGE_FOUND = "Recurso encontrado.";
    public static final String MESSAGE_DELETED = "Recurso eliminado.";

    // Mensajes de error
    public static final String MESSAGE_ERROR = "Ocurrió un error interno en el servidor.";
    public static final String MESSAGE_ERROR_CREATION = "No se pudo crear el recurso.";
    public static final String MESSAGE_ERROR_UPDATE = "No se pudo actualizar el recurso.";
    public static final String MESSAGE_NOT_FOUND = "Recurso no encontrado.";
    public static final String MESSAGE_BAD_REQUEST = "La solicitud contiene datos incorrectos o incompletos.";

    public static final String MESSAGE_REQUIRED_TOKEN = "El token es requerido";
    public static final String MESSAGE_INVALID_TOKEN = "El token JWT está vacío o nulo.";

    public static final String MESSAGE_EMPTY_LIST_PRODUCTS = "Error al obtener la lista de productos disponibles.";
    public static final String MESSAGE_INVALID_PRODUCTS_IDS = "Algunos productos no existen.";
    public static final String MESSAGE_USER_NOT_FOUND = "No se encontró el usuario asociado al token.";

    // Códigos de estado HTTP
    public static final int CODE_SUCCESSFUL = 200;
    public static final int CODE_CREATED = 201;
    public static final int CODE_BAD_REQUEST = 400;
    public static final int CODE_UNAUTHORIZED = 401;
    public static final int CODE_NOT_FOUND = 404;
    public static final int CODE_ERROR = 500;

    // url
    public static final String ADMIN_ACCESS_ENDPOINTS = "/ordenes/listar";
    public static final String USER_ACCESS_ENDPOINTS = "/ordenes/crear";
}
