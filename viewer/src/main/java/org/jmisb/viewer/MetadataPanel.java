package org.jmisb.viewer;

import org.jmisb.api.klv.st0601.IUasDatalinkValue;
import org.jmisb.api.klv.st0601.UasDatalinkMessage;
import org.jmisb.api.klv.st0601.UasDatalinkTag;
import org.jmisb.api.klv.st0601.NestedVmtiLocalSet;
import org.jmisb.api.klv.st0903.VmtiLocalSet;
import org.jmisb.api.klv.st0903.VmtiMetadataKey;
import org.jmisb.api.klv.st0903.IVmtiMetadataValue;
import org.jmisb.api.klv.st0903.VTargetSeries;
import org.jmisb.api.klv.st0903.vtarget.VTargetPack;
import org.jmisb.api.klv.st0903.vtarget.VTargetMetadataKey;

import org.jmisb.api.video.IMetadataListener;
import org.jmisb.api.video.MetadataFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

import static java.awt.Font.PLAIN;

/**
 * Simple text pane to display MISB metadata
 */
public class MetadataPanel extends JTextPane implements IMetadataListener
{
    private static Logger logger = LoggerFactory.getLogger(MetadataPanel.class);
    private long previous = 0;

    /**
     * Constructor
     */
    MetadataPanel()
    {
        setEditable(false);
        setContentType("text/html");
        setFont(new Font("Dialog", PLAIN, 12));
        clear();
    }

    @Override
    public void updateUI()
    {
        super.updateUI();
        // Make HTML renderer use the component's font setting
        putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);
    }

    private String tryFormatVmtiLS(IUasDatalinkValue value)
    {
        if (value instanceof NestedVmtiLocalSet == false)
        {
            return "";
        }

        VmtiLocalSet vmtiLS = ((NestedVmtiLocalSet)value).getVmti();
        return formatVmtiLS(vmtiLS);
    }

    private String formatVmtiLS(VmtiLocalSet vmti)
    {
        StringBuilder sb = new StringBuilder();
        for (VmtiMetadataKey tag : vmti.getTags())
        {
            IVmtiMetadataValue value = vmti.getField(tag);
            String targetsStr = tryFormatTargetSeries(value);
            if (targetsStr != "")
            {
                sb.append(targetsStr);
            }
            else
            {
                sb.append("<b>VMTI-").append(tag).append(":</b> ").append(value.getDisplayableValue()).append("<br>");
            }
        }
        return sb.toString();
    }

    private String tryFormatTargetSeries(IVmtiMetadataValue value)
    {
        if (value instanceof VTargetSeries == false)
        {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        java.util.List<VTargetPack> tgtList = ((VTargetSeries)value).getVTargets();
        for (VTargetPack target : tgtList)
        {
            sb.append("id ").append(target.getTargetIdentifier()).append("<br>");
            for (VTargetMetadataKey tag : target.getTags())
            {
                IVmtiMetadataValue tgtValue = target.getField(tag);
                sb.append(tag).append(": ").append(tgtValue.getDisplayableValue()).append("<br>");
            }
        }

        return sb.toString();
    }

    @Override
    public void onMetadataReceived(MetadataFrame metadataFrame)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("pts = " + metadataFrame.getPts());
        }

        // Refresh at most once per second
        long current = System.nanoTime();
        if ((current - previous) > 1_000_000_000)
        {
            SwingUtilities.invokeLater(() ->
            {
                StringBuilder sb = new StringBuilder();
                sb.append("<html>");
                sb.append("<head>");
                sb.append("</head>");
                sb.append("<body>");

                sb.append("<h1>");
                sb.append(metadataFrame.getMisbMessage().displayHeader());
                sb.append("</h1>");
                // TODO: handle other message types, including nested local sets
                if (metadataFrame.getMisbMessage() instanceof UasDatalinkMessage)
                {
                    UasDatalinkMessage uasDatalinkMessage = (UasDatalinkMessage) metadataFrame.getMisbMessage();
                    for (UasDatalinkTag tag : uasDatalinkMessage.getTags())
                    {
                        IUasDatalinkValue value = uasDatalinkMessage.getField(tag);
                        
                        String vmtiStr = tryFormatVmtiLS(value);
                        if (vmtiStr != "")
                        {
                            sb.append(vmtiStr);
                        }
                        else
                        {
                            sb.append("<b>").append(tag).append(":</b> ").append(value.getDisplayableValue()).append("<br>");
                        }
                                    
                    }
                }

                sb.append("</body>");
                sb.append("</html>");
                setText(sb.toString());
            });

            previous = current;
        }
    }

    public final void clear() {
        this.setText("<html><head/><body/></html>");
    }
}
