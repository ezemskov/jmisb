package org.jmisb.api.klv.st0903.vtarget;

/**
 * Target Location Offset Longitude (ST0903 VTarget Pack Tag 11)
 * <p>
 * From ST0903:
 * <blockquote>
 * Longitude offset for target from Frame Center Longitude (MISB ST 0601), based
 * on WGS84 ellipsoid. Has meaning only when embedding the VMTI LS in ST 0601
 * LS. The Target Location Longitude Offset adds to the Frame Center Longitude
 * metadata item from the parent ST 0601 to determine the Longitude of the
 * target. Both data items need to be in decimal representation prior to
 * addition to determine the actual measured or calculated Motion Imagery target
 * location.
 * <p>
 * Target Location Longitude Offset has a real earth coordinate represented by a
 * latitude-longitude pair. Target locations that lie above the horizon do not
 * correspond to a point on the earth. Also, target locations may lie outside of
 * the mapped range. Both cases should either not be reported or be reported as
 * an “error”. Conversion: IMAPB(-19.2, 19.2, 3).
 * <p>
 * Valid Values: The set of real numbers from -19.2 to 19.2 inclusive.
 * </blockquote>
 */
public class TargetLocationOffsetLon extends AbstractTargetLocationOffset
{
    /**
     * Create from value
     *
     * @param offset longitude offset in degrees. Valid range is [-19.2, 19.2]
     */
    public TargetLocationOffsetLon(double offset)
    {
        super(offset);
    }

    /**
     * Create from encoded bytes.
     *
     * @param bytes Encoded byte array
     */
    public TargetLocationOffsetLon(byte[] bytes)
    {
        super(bytes);
    }


    @Override
    public final String getDisplayName()
    {
        return "Target Location Offset Longitude";
    }
}