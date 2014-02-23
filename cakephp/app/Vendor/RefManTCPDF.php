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
		$table = '<table style="border: 1px solid #5E7796;">';

		// table head
		$table .= '<thead><tr>';
		foreach($header as $headercell) {
			$table .= sprintf('<th style="border-right: 1px solid #FCF1D4; border-left: 1px solid #FCF1D4; font-size: %spt; color: #FFFFFF; background-color: #5E7796; font-weight: bold;" width="%s">%s</th>',
												PDF_FONT_SIZE_DATA,
												$headercell['width'],
												$headercell['text']);
		}
		$table .= '</tr></thead>';

		// data
		$table .= '<tbody>';

		$odd = false;
		foreach($data as $datarow) {
			$table .= sprintf('<tr style="%s">', $datarow['style']);
			$iCell = 0;
			if (empty($datarow['data'][0]['text'])) {
				$odd = !$odd;
			}
			foreach($datarow['data'] as $datacell) {
				$table .= sprintf('<td style="font-size: %spt; color: #000020; background-color: %s; %s " %s>%s</td>',
													PDF_FONT_SIZE_DATA,
													($odd) ? '#E9ECEE' : '#FFFFFF',
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

