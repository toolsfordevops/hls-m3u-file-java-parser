package io.t12s.oss.hls.playlist.tag.entity;

/**
 * Each Media Segment is specified by a series of Media Segment tags
 * followed by a URI.  Some Media Segment tags apply to just the next
 * segment; others apply to all subsequent segments until another
 * instance of the same tag.
 * A Media Segment tag MUST NOT appear in a Master Playlist.  Clients
 * MUST fail to parse Playlists that contain both Media Segment tags and
 * Master Playlist tags (Section 4.3.4). - see <a href="https://datatracker.ietf.org/doc/html/rfc8216#section-4.3.2">rfc8216#section-4.3.2</a>
 */
public enum MediaSegmentTag implements HLSTagName {
    /**
     * The EXTINF tag specifies the duration of a Media Segment.  It applies
     * only to the next Media Segment.  This tag is REQUIRED for each Media
     * Segment.  Its format is:
     * <pre>{@code
     * #EXTINF:<duration>,[<title>]
     * }</pre>
     * <p>
     * where duration is a decimal-floating-point or decimal-integer number
     * (as described in <a href="https://datatracker.ietf.org/doc/html/rfc8216#section-4.2">Section 4.2</a>) that specifies the duration of the
     * Media Segment in seconds.  Durations SHOULD be decimal-floating-
     * point, with enough accuracy to avoid perceptible error when segment
     * durations are accumulated.  However, if the compatibility version
     * number is less than 3, durations MUST be integers.  Durations that
     * are reported as integers SHOULD be rounded to the nearest integer.
     * The remainder of the line following the comma is an optional human-
     * readable informative title of the Media Segment expressed as UTF-8
     * text.
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc8216#section-4.3.2.1">rfc8216#section-4.3.2.1</a>
     */
    EXTINF("EXTINF", true),

    /**
     * The EXT-X-BYTERANGE tag indicates that a Media Segment is a sub-range
     * of the resource identified by its URI.  It applies only to the next
     * URI line that follows it in the Playlist.  Its format is:
     * <pre>{@code
     * #EXT-X-BYTERANGE:<n>[@<o>]
     * }</pre>
     * <p>
     * where n is a decimal-integer indicating the length of the sub-range
     * in bytes.  If present, o is a decimal-integer indicating the start of
     * the sub-range, as a byte offset from the beginning of the resource.
     * If o is not present, the sub-range begins at the next byte following
     * the sub-range of the previous Media Segment.
     * <p>
     * If o is not present, a previous Media Segment MUST appear in the
     * Playlist file and MUST be a sub-range of the same media resource, or
     * the Media Segment is undefined and the client MUST fail to parse the
     * Playlist.
     * <p>
     * A Media Segment without an {@code EXT-X-BYTERANGE} tag consists of the entire
     * resource identified by its URI.
     * <p>
     * Use of the {@code EXT-X-BYTERANGE} tag REQUIRES a compatibility version
     * number of 4 or greater.
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc8216#section-4.3.2.2">rfc8216#section-4.3.2.2</a>
     */
    EXT_X_BYTERANGE("EXT-X-BYTERANGE", true),

    /**
     * The {@code EXT-X-DISCONTINUITY} tag indicates a discontinuity between the
     * Media Segment that follows it and the one that preceded it. Its format is:
     * <pre>{@code
     * #EXT-X-DISCONTINUITY
     * }</pre>
     * <p>
     * The {@code EXT-X-DISCONTINUITY} tag MUST be present if there is a change in
     * any of the following characteristics:
     * <ul>
     *  <li>file format</li>
     *  <li>number, type, and identifiers of tracks</li>
     *  <li>timestamp sequence</li>
     * </ul>
     * The {@code EXT-X-DISCONTINUITY} tag SHOULD be present if there is a change in
     * any of the following characteristics:
     *
     * <ul>
     *  <li>encoding parameters</li>
     *  <li>encoding sequence</li>
     * </ul>
     * See Sections <a href="https://datatracker.ietf.org/doc/html/rfc8216#section-3">3</a>,
     * <a href="https://datatracker.ietf.org/doc/html/rfc8216#section-6.2.1">6.2.1</a>,
     * and <a href="https://datatracker.ietf.org/doc/html/rfc8216#section-6.3.3">6.3.3</a> for more information about the
     * {@code EXT-X-DISCONTINUITY} tag.
     */
    EXT_X_DISCONTINUITY("EXT-X-DISCONTINUITY", false),

    /**
     * Media Segments MAY be encrypted.  The EXT-X-KEY tag specifies how to
     * decrypt them.  It applies to every Media Segment and to every Media
     * Initialization Section declared by an EXT-X-MAP tag that appears
     * between it and the next EXT-X-KEY tag in the Playlist file with the
     * same KEYFORMAT attribute (or the end of the Playlist file).  Two or
     * more EXT-X-KEY tags with different KEYFORMAT attributes MAY apply to
     * the same Media Segment if they ultimately produce the same decryption
     * key.  The format is:
     * <pre>{@code
     * #EXT-X-KEY:<attribute-list>
     * }</pre>
     * <p>
     * The following attributes are defined:
     * <p>
     * <pre>METHOD</pre>
     * The value is an enumerated-string that specifies the encryption
     * method.  This attribute is REQUIRED.
     * The methods defined are: NONE, AES-128, and SAMPLE-AES.
     * An encryption method of NONE means that Media Segments are not
     * encrypted.  If the encryption method is NONE, other attributes
     * MUST NOT be present.
     * An encryption method of AES-128 signals that Media Segments are
     * completely encrypted using the Advanced Encryption Standard (AES)
     * [AES_128] with a 128-bit key, Cipher Block Chaining (CBC), and
     * Public-Key Cryptography Standards #7 (PKCS7) padding [RFC5652].
     * CBC is restarted on each segment boundary, using either the
     * Initialization Vector (IV) attribute value or the Media Sequence
     * Number as the IV; see Section 5.2.
     * An encryption method of SAMPLE-AES means that the Media Segments
     * contain media samples, such as audio or video, that are encrypted
     * using the Advanced Encryption Standard [AES_128].  How these media
     * streams are encrypted and encapsulated in a segment depends on the
     * media encoding and the media format of the segment.  fMP4 Media
     * Segments are encrypted using the 'cbcs' scheme of Common
     * Encryption [COMMON_ENC].  Encryption of other Media Segment
     * formats containing H.264 [H_264], AAC [ISO_14496], AC-3 [AC_3],
     * and Enhanced AC-3 [AC_3] media streams is described in the HTTP
     * Live Streaming (HLS) Sample Encryption specification [SampleEnc].
     * The IV attribute MAY be present; see Section 5.2.
     * </p>
     * <p>
     * <pre>URI</pre>
     * The value is a quoted-string containing a URI that specifies how
     * to obtain the key.  This attribute is REQUIRED unless the METHOD
     * is NONE.
     * </p>
     * <p>
     * <pre>IV</pre>
     * The value is a hexadecimal-sequence that specifies a 128-bit
     * unsigned integer Initialization Vector to be used with the key.
     * Use of the IV attribute REQUIRES a compatibility version number of
     * 2 or greater.  See Section 5.2 for when the IV attribute is used.
     * </p>
     * <p>
     * <pre>KEYFORMAT</pre>
     * The value is a quoted-string that specifies how the key is
     * represented in the resource identified by the URI; see Section 5
     * for more detail.  This attribute is OPTIONAL; its absence
     * indicates an implicit value of "identity".  Use of the KEYFORMAT
     * attribute REQUIRES a compatibility version number of 5 or greater.
     * </p>
     * <p>
     * <pre>KEYFORMATVERSIONS</pre>
     * The value is a quoted-string containing one or more positive
     * integers separated by the "/" character (for example, "1", "1/2",
     * or "1/2/5").  If more than one version of a particular KEYFORMAT
     * is defined, this attribute can be used to indicate which
     * version(s) this instance complies with.  This attribute is
     * OPTIONAL; if it is not present, its value is considered to be "1".
     * Use of the KEYFORMATVERSIONS attribute REQUIRES a compatibility
     * version number of 5 or greater.
     * </p>
     * </p>
     *
     * @see <a href="https://datatracker.ietf.org/doc/html/rfc8216#section-4.3.2.4">rfc8216#section-4.3.2.4</a>
     */
    EXT_X_KEY("EXT-X-KEY", true),

    /**
     * The EXT-X-MAP tag specifies how to obtain the Media Initialization
     * Section (Section 3) required to parse the applicable Media Segments.
     * It applies to every Media Segment that appears after it in the
     * Playlist until the next EXT-X-MAP tag or until the end of the
     * Playlist. Its format is:
     * <pre>{@code
     * #EXT-X-MAP:<attribute-list>
     * }</pre>
     * The following attributes are defined:
     * <p>
     * <p>
     * <pre>{@code URI}</pre>
     * <p>
     * The value is a quoted-string containing a URI that identifies a
     * resource that contains the Media Initialization Section.  This
     * attribute is REQUIRED.
     * </p>
     * <p>
     * <pre>{@code BYTERANGE}</pre>
     * <p>
     * The value is a quoted-string specifying a byte range into the
     * resource identified by the URI attribute.  This range SHOULD
     * contain only the Media Initialization Section.  The format of the
     * byte range is described in Section 4.3.2.2.  This attribute is
     * OPTIONAL; if it is not present, the byte range is the entire
     * resource indicated by the URI.
     * </p>
     * </p>
     * <br>
     * <p>
     * An EXT-X-MAP tag SHOULD be supplied for Media Segments in Playlists
     * with the EXT-X-I-FRAMES-ONLY tag when the first Media Segment (i.e.,
     * I-frame) in the Playlist (or the first segment following an EXT-
     * X-DISCONTINUITY tag) does not immediately follow the Media
     * Initialization Section at the beginning of its resource.
     * <p>
     * Use of the EXT-X-MAP tag in a Media Playlist that contains the EXT-
     * X-I-FRAMES-ONLY tag REQUIRES a compatibility version number of 5 or
     * greater.  Use of the EXT-X-MAP tag in a Media Playlist that DOES NOT
     * contain the EXT-X-I-FRAMES-ONLY tag REQUIRES a compatibility version
     * number of 6 or greater.
     * <p>
     * If the Media Initialization Section declared by an EXT-X-MAP tag is
     * encrypted with a METHOD of AES-128, the IV attribute of the EXT-X-KEY
     * tag that applies to the EXT-X-MAP is REQUIRED.
     */
    EXT_X_MAP("EXT-X-MAP", true);

    public final String tagName;
    public final boolean withValue;

    MediaSegmentTag(final String tagName,
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
