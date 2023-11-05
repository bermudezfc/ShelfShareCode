import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;

public class NoScalingIcon implements Icon{ 
//This class' purpose is to prevent the images from loading with a bad quality, this is an inheritance
//Note: I made sure to import images quite bigger than intended as this class loads the image slightly smaller
	private Icon icon;
	
	public NoScalingIcon(Icon icon)
    {
        this.icon = icon;
    }

    public int getIconWidth()
    {
        return icon.getIconWidth();
    }

    public int getIconHeight()
    {
        return icon.getIconHeight();
    }
    
    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        Graphics2D g2d = (Graphics2D)g.create();

        AffineTransform at = g2d.getTransform();

        int scaleX = (int)(x * at.getScaleX()); //gets the x Scale
        int scaleY = (int)(y * at.getScaleY()); //gets the y Scale

        int locationX = scaleX;
        int locationY = scaleY;

        AffineTransform scaled = AffineTransform.getScaleInstance(0.7 / at.getScaleX(), 0.7 / at.getScaleY());
        at.concatenate( scaled );
        g2d.setTransform( at );

        icon.paintIcon(c, g2d, locationX, locationY);

        g2d.dispose();
    }

}
