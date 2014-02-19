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
		$this->Cell(0, 8, $this->headerText, 'B', 1, 'L', false);
	}

	/**
	 * Overwrites the default footer.
	 */
	public function Footer() {
		$this->SetY(-15);
		$this->Cell(0, 8, sprintf($this->footerText, $this->getAliasNumPage(), $this->getAliasNbPages()), 'T', 1, 'L', false);
	}

	/**
	 * Prints table.
	 */
	public function getTable($header, $data) {

		// start table
		$table = '<table>';

		// header
		$table .= '<thead><tr>';
		foreach($header as $headercell) {
			$table .= sprintf('<th style="border-bottom: .5px dotted #333333; font-size: 9pt; background-color: #CCCCCC;" width="%s">%s</th>', $headercell['width'], $headercell['text']);
		}
		$table .= '</tr></thead>';

		// data
		$table .= '<tbody>';

		$altstyle[0] = 'background-color: #EEEEFF; ';
		$altstyle[1] = 'background-color: #FFFFFF; ';

		$odd = false;
		foreach($data as $datarow) {
			$table .= sprintf('<tr style="font-size: 10pt; %s ">', $datarow['style']);
			$iCell = 0;
			if (empty($datarow['data'][0]['text'])) {
				$odd = !$odd;
			}
			foreach($datarow['data'] as $datacell) {
				$table .= sprintf('<td style="font-size: 10pt; background-color: %s; %s " %s>%s</td>',
													($odd) ? '#EEEEFF' : '#FFFFFF',
													$datarow['style'],
													(empty($datacell['colspan']) ? sprintf('width="%s"', $header[$iCell]['width']) : sprintf('colspan="%s"', $datacell['colspan'])),
													$datacell['text']);
				$iCell++;
			}
			$table .= '</tr>';
			$odd = !$odd;
		}
		$table .= '</tbody>';

		// end table
		$table .= '</table>';

		return $table;
	}

}

/* EOF */

