package com.rafaG.rentacar.Constants;

public interface Constants {
    //Constantes relacionados a columnas de las tablas
        //users
        public static final String ID="id";
        public static final String NAME= "name";
        public static final String APELLIDO = "apellido";
        public static final String EMAIL = "email";
        public static final String PASSWORD= "password";
        public static final String EMAIL_VERIFIED_AT ="email_verified_at";
        public static final String REMEMBER_TOKEN ="remember_token";
        public static final String CEDULA= "cedula";
        public static final String NO_LICENCIA="no_licencia";
        public static final String FOTO_PERFIL = "foto_perfil";
        public static final String FOTO_LICENCIA = "foto_licencia";
        public static final String ROL ="rol";
        public static final String ESTADO= "estado";
        public static final String FECHA_NAC ="fecha_nac";
        public static final String FECHA_VENC ="fecha_venc";
        public static final String CREATED_AT ="created_at";
        public static final  String UPDATED_AT = "updated_at";
    //CLOSE
    //Constantes relacionadas a los mensajes que se le envian al usuario
    public static final  String TYPE = "type";
    public static final  String MESSAGE = "message";
    public static final  String TYPE_REGISTER = "type_register";
    public static final  String ERROR_REGISTER = "error_register";
    public static final  String REGISTER_CLEAR = "El registro ha sido creado correctamente para el usuario: ";
    public static final  String REGISTER_FAILED = "El registro ha fallado, intentelo de nuevo";

}
