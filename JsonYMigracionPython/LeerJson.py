import json
import urllib.request
import urllib.error
import sqlite3

# Crear conexión
conn = sqlite3.connect("lab4_tp2.db")

# Crear puntero
cursor = conn.cursor()

# Crear Tabla pais
cursor.execute('''CREATE TABLE IF NOT EXISTS pais
                (codigo_pais INTEGER PRIMARY KEY,
                nombre_pais TEXT(65) NOT NULL,
                capital_pais TEXT(50) NOT NULL,
                region TEXT(50) NOT NULL,
                poblacion INTEGER,
                latitud REAL,
                longitud REAL)''')

"""Ejecute una llamada mediante a la siguiente URL tipo RESTful
https://restcountries.eu/rest/v2/callingcode/{callingcode}
Obtenga la información y migre la misma a la tabla país creada anteriormente. El proceso debe ejecutarse
para los códigos desde 1 hasta 300, contemplando que si alguno de los códigos no retorna datos se
continúe con el siguiente."""

for codigo in range(1, 300):
    url = "https://restcountries.eu/rest/v2/callingcode/" + str(codigo)
    try:
        data = urllib.request.urlopen(url).read().decode()
        dataJson = json.loads(data)

        for i in dataJson:
            codigoPais = i['callingCodes'][0]
            nombrePais = i['name']
            capitalPais = i['capital']
            region = i['region']
            poblacion = i['population']
            latitud = i['latlng'][0]
            longitud = i['latlng'][1]
            cursor.execute("SELECT * FROM pais WHERE codigo_pais = ?", [codigoPais])
            existePais = cursor.fetchone()
            if existePais is not None:
                cursor.execute("UPDATE pais SET "
                               "nombre_pais = ?, "
                               "capital_pais = ?, "
                               "region = ?, "
                               "poblacion = ?, "
                               "latitud = ?, "
                               "longitud = ? "
                               "WHERE codigo_pais = ?",
                               (nombrePais, capitalPais, region, poblacion, latitud, longitud, codigoPais))
                print("actualizado")
                conn.commit()
            else:
                cursor.execute("INSERT INTO pais VALUES (?,?,?,?,?,?,?)",
                               (codigoPais, nombrePais, capitalPais, region, poblacion, latitud, longitud))
                print("guardado")
                conn.commit()
    except urllib.error.HTTPError:
        print("Error al leer url")

cursor.close()
conn.close()
