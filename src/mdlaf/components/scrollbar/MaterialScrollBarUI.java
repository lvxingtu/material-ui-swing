package mdlaf.components.scrollbar;

import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.*;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/*
 * Contributed by https://github.com/downToHell
 * Contributed for refactoring by https://github.com/vincenzopalazzo
 * */

public class MaterialScrollBarUI extends BasicScrollBarUI {

	public static ComponentUI createUI (JComponent c) {
		return new MaterialScrollBarUI ();
	}

	@Override
	public void installUI (JComponent c) {
		super.installUI (c);
	}

	@Override
	public void paint (Graphics g, JComponent c) {
		super.paint (MaterialDrawingUtils.getAliasedGraphics (g), c);
	}

	@Override
	protected JButton createDecreaseButton (int orientation) {
		return installButton(orientation);
	}

	@Override
	protected JButton createIncreaseButton (int orientation) {
		return installButton(orientation);
	}

	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
		super.paintTrack(g, c, trackBounds);
		g.setColor(UIManager.getColor ("ScrollBar.track"));
	}

	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
		super.paintThumb(g, c, thumbBounds);
		g.setColor(UIManager.getColor ("ScrollBar.thumb"));

	}

	/**
	 * Method service for not duplicate code
	 * @author https://github.com/vincenzopalazzo
	 * @param orientation
	 * @return JButton with correct orientation
	 */
	private JButton installButton(int orientation){
		//JButton button = new MaterialArrowButtonUI(orientation);
		JButton button = new JButton();
		MaterialManagerListener.removeAllMouseListener(button);
		button.setOpaque (true);
		button.setBackground (UIManager.getColor ("ScrollBar.arrowButtonBackground"));
		setIconArrowButton(button, orientation);
		if(UIManager.getBoolean("ScrollBar[MouseHover].enable")){
			button.addMouseListener(MaterialUIMovement.getStaticMovement(button,UIManager.getColor("ScrollBar[MouseHover].color"),
					UIManager.getColor("ScrollBar[OnClick].color")));
		}
		button.setBorder (BorderFactory.createEmptyBorder());
		return button;
	}

	/**
	 * This is method of service for setting icon on button
	 * because the arrow button border is painted not correct in JScorllbar
	 * @param button
	 * @param orientation
	 */
	private void setIconArrowButton(JButton button, int orientation) {
		if(button == null){
			throw new IllegalArgumentException("Input null");
		}
		if (orientation == SwingConstants.NORTH){
			button.setIcon(new ImageIcon(MaterialImageFactory.getIstance().getImage(MaterialImageFactory.UP_ARROW)));
			return;
		}else if(orientation == SwingConstants.SOUTH){
			button.setIcon(new ImageIcon(MaterialImageFactory.getIstance().getImage(MaterialImageFactory.DOWN_ARROW)));
			return;
		}else if(orientation == SwingConstants.EAST){
			button.setIcon(new ImageIcon(MaterialImageFactory.getIstance().getImage(MaterialImageFactory.RIGHT_ARROW)));
			return;
		}else if(orientation == SwingConstants.WEST){
			button.setIcon(new ImageIcon(MaterialImageFactory.getIstance().getImage(MaterialImageFactory.LEFT_ARROW)));
			return;
		}
		throw new IllegalArgumentException("orientation not valid");
	}


	@Override
	protected void configureScrollBarColors() {
		super.configureScrollBarColors();
		thumbDarkShadowColor = UIManager.getColor ("ScrollBar.thumbDarkShadow");
		thumbHighlightColor = UIManager.getColor ("ScrollBar.thumbHighlight");
		thumbLightShadowColor = UIManager.getColor ("ScrollBar.thumbShadow");
	}
}