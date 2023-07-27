package exception;

public class IsiUnsupportedExtensionException extends RuntimeException {
    public IsiUnsupportedExtensionException(String extension) {
        super("ERROR - Unsupported extension " + extension + " can only compile .isi files.");
    }
}
