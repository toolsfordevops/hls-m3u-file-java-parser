package io.t12s.oss.hls.playlist.tag.entity;

/**
 * Playlist tags specify either global parameters of the Playlist or
 * information about the Media Segments or Media Playlists that appear
 * after them.
 * - see <a href="https://datatracker.ietf.org/doc/html/rfc8216#section-4.3">rfc8216#section-4.3</a>
 * <p>
 * <p>
 * These tags are allowed in both Media Playlists and Master Playlists.
 * - see <a href="https://datatracker.ietf.org/doc/html/rfc8216#section-4.3.1">rfc8216#section-4.3.1</a>
 */
public enum BasicTagName implements HLSTagName {
    /**
     * The EXTM3U tag indicates that the file is an Extended M3U [M3U]
     * Playlist file.  It MUST be the first line of every Media Playlist and
     * every Master Playlist.  Its format is:
     *
     * <pre>{@code
     * #EXTM3U
     * }</pre>
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc8216#section-4.3.1.1">rfc8216#section-4.3.1.1</a>
     */
    EXTM3U("EXTM3U", false),

    /**
     * The EXT-X-VERSION tag indicates the compatibility version of the
     * Playlist file, its associated media, and its server.
     * <p>
     * The EXT-X-VERSION tag applies to the entire Playlist file.  Its
     * format is:
     *
     * <pre>{@code
     * #EXT-X-VERSION:<n>
     * }</pre>
     * Where {@code n} is an integer indicating the protocol compatibility version
     * number.
     *
     * @see <a href=https://datatracker.ietf.org/doc/html/rfc8216#section-4.3.1.2">rfc8216#section-4.3.1.2</a>
     */
    EXT_X_VERSION("EXT-X-VERSION", true);

    private final String tagName;
    private final boolean withValue;

    BasicTagName(final String tagName,
                 final boolean withValue) {
        this.tagName = tagName;
        this.withValue = withValue;
    }

    public String getTagName() {
        return tagName;
    }

    public boolean isWithValue() {
        return withValue;
    }
}
