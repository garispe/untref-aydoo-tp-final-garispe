package untref.aydoo.procesador;

import java.io.File;
import java.io.IOException;

import org.ho.yaml.Yaml;

public class ManejadorYML {

	File directorio;

	public ManejadorYML(String rutaDir) {

		this.directorio = new File(rutaDir);
	}

	public void escribirYML(Resultado resultado) throws IOException {

		Yaml.dump(resultado, new File(directorio.getPath() + "/resultado.yml"));
	}
}
