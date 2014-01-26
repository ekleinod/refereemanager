<?php
App::import('Vendor','TCPDF/tcpdf');

/**
* RefManTCPDF: TCPDF header and footer definition.
 *
 * @package       Vendor
 */
class RefManTCPDF extends TCPDF {

	private $headerText = null;
	private $footerText = null;

	/**
	 * Sets settings for header and footer.
	 */
	public function rmSetHeader($theHeaderText, $theFooterText) {
		$this->headerText = $theHeaderText;
		$this->footerText = $theFooterText;
	}

	/**
	 * Overwrites the default header.
	 */
	public function Header() {
		$this->setY(10);
		$this->Cell(0, 10, $this->headerText, 'B', 1, 'L', false);
	}

	/**
	 * Overwrites the default footer.
	 */
	public function Footer() {
		$this->SetY(-15);
		$this->Cell(0, 10, sprintf($this->footerText, $this->getAliasNumPage(), $this->getAliasNbPages()), 'T', 1, 'C', false);
	}

}

/* EOF */

