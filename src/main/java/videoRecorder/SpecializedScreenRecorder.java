package videoRecorder;

import org.monte.media.Format;
import org.monte.media.Registry;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//Tenemos que heredar de ScreenRecorder, y cuando nos salga error creamos el constructor con la ultima opcion que aparezca
public class SpecializedScreenRecorder extends ScreenRecorder {

	//Creamos esta variable para darle un comienzo al nombre al archivo de video creado
	private String name;
	private File movieFile;

	public String getFileName() {
		return fileName;
	}

	private String fileName;

	//Añadimos el parametro de "String name" al final
	public SpecializedScreenRecorder(GraphicsConfiguration cfg, Rectangle captureArea,
									 Format fileFormat, Format screenFormat,
									 Format mouseFormat, Format audioFormat,
									 File movieFolder, String name) throws IOException, AWTException {
		super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
		//Hacemos un get a la variable privada
		this.name = name;
		movieFile = createMovieFile(fileFormat);
		setMaxRecordingTime(5*60*1000);



	}

	//Método para crear el archivo de video
	@Override
	protected File createMovieFile(Format fileFormat) throws IOException {

		//Con esto digo que si no existe la variable movieFolder, la cree
		if(!movieFolder.exists()){

			movieFolder.mkdirs();

			//En el caso de que exista pero no sea un directorio, voy a tirar una excepcion
		}else if(!movieFolder.isDirectory()){

			//Tiramos una excepción
			throw new IOException("\"" + movieFolder + "\" is not a directory");

		}

		//Creamos un objeto SimpleDateFormat, que nos da el formato en el que quiero que se despliegue
		//Año-Mes-Dia Hora
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH");

		String formatName = Registry.getInstance().getExtension(fileFormat);
		fileName = name + "-" + dataFormat.format(new Date()) + "." + formatName;
		//Devolvemos el archivo con el formato que especificamos
		return new File(movieFolder, fileName);

	}





}

