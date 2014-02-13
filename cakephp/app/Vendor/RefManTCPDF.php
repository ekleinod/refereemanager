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
	public function printTable($header, $data) {

		// header
		$this->SetFillColor(255, 0, 0);
		$this->SetTextColor(255);
		$this->SetDrawColor(128, 0, 0);
		$this->SetLineWidth(0.3);
		$this->SetFont('', 'B');
		$this->printRow($header);

		// save widths
		$widths = array();
		foreach ($header as $headerdef) {
			$widths[] = $headerdef['width'];
		}

		foreach($data as &$editrow) {
			for ($i = 0; $i < count($widths); $i++) {
				$editrow[$i]['width'] = $widths[$i];
			}
		}

		//$this->Ln();

		// data
		$this->SetFillColor(224, 235, 255);
		$this->SetTextColor(0);
		$this->SetFont('');
		foreach($data as $row) {
			$this->printRow($row);
		}
		//$this->Cell(array_sum($w), 0, '', 'T');
	}

	public function printRow($row) {

		$page_start = $this->getPage();
		$y_start = $this->GetY();

		// write cells
		foreach ($row as $cell) {
			$this->MultiCell($cell['width'], 0, $cell['text'], 'RB', 'L', true, 0, '', '', true, 0);
		}

		$this->Ln();
/*
		$page_end_1 = $this->getPage();
		$y_end_1 = $this->GetY();

		$this->setPage($page_start);

		// write the right cell
		$this->MultiCell(0, 0, $right, 1, 'J', 0, 1, $this->GetX() ,$y_start, true, 0);

		$page_end_2 = $this->getPage();
		$y_end_2 = $this->GetY();

		// set the new row position by case
		if (max($page_end_1,$page_end_2) == $page_start) {
		$ynew = max($y_end_1, $y_end_2);
		} elseif ($page_end_1 == $page_end_2) {
		$ynew = max($y_end_1, $y_end_2);
		} elseif ($page_end_1 > $page_end_2) {
		$ynew = $y_end_1;
		} else {
		$ynew = $y_end_2;
		}

		$this->setPage(max($page_end_1,$page_end_2));
		$this->SetXY($this->GetX(),$ynew);*/
	}

}

/* EOF */

