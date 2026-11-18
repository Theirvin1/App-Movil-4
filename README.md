# Práctica UTEQ – Supabase en Android con Kotlin

Aplicación Android (Kotlin + XML) que se conecta a **Supabase** con el SDK `supabase-kt` (Postgrest), consulta la tabla `public.alumnos` y muestra los registros ordenados alfabéticamente. Sigue la *Guía práctica 02 – Integración de Supabase en Android con Kotlin*.

## Qué se hizo
1. **Base de datos**: tabla `public.alumnos` con RLS y política de lectura demo (`supabase/setup_alumnos.sql`).
2. **Gradle**: Supabase BOM 3.6.0, Ktor 3.5.0, plugin de serialización y `buildConfig` con `SUPABASE_URL` y `SUPABASE_KEY` leídos de `local.properties`.
3. **Permiso** `INTERNET` en el `AndroidManifest.xml`.
4. **Código**: `data/SupabaseManager.kt` (cliente único), `models/Alumno.kt` (`@Serializable`), `utils/SupabaseErrorHandler.kt` (diálogo de error) y `MainActivity.kt` (consulta con corutinas).
5. **Interfaz de referencia**:
   - Imagen arriba (logo UTEQ).
   - Texto verde **"Lista Estudiante"** pegado debajo de la imagen.
   - Multi text (`EditText` multilínea) expandido al resto de la pantalla en modo **solo lectura** (viewOnly: sin foco, sin teclado, `keyListener = null`), donde se listan los alumnos.

## Captura de referencia
![Interfaz Lista Estudiante](capturas/interfaz_lista_estudiante.png)

## Configuración
Crear `local.properties` en la raíz (no se sube al repositorio):
```
SUPABASE_URL=https://TU_PROYECTO.supabase.co
SUPABASE_KEY=TU_CLAVE_PUBLICABLE
```
Usar solo la clave publicable/anon, nunca `service_role` ni el JWT secret.
