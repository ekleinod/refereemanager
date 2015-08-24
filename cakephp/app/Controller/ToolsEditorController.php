<?php

App::uses('AppController', 'Controller');
App::uses('CakeEmail', 'Network/Email');
App::uses('RefManPeople', 'Utility');
App::uses('RefManRefereeFormat', 'Utility');
App::uses('RefManTemplate', 'Utility');

/**
 * Tools editor Controller
 *
 * @author ekleinod (ekleinod@edgesoft.de)
 * @version 0.3
 * @since 0.1
 */
class ToolsEditorController extends AppController {

	/** Helper classes. */
	public $helpers = array('RefereeFormat');

	/** Models. */
	public $uses = array('Referee', 'Season');

	/**
	 * Defines actions to perform before the action method is executed.
	 */
	public function beforeFilter() {
		parent::beforeFilter();

		if (!$this->viewVars['isEditor']) {
			$this->Session->setFlash(__('Der Zugriff ist nur für Editoren erlaubt. Bitte loggen Sie sich ein.'));
			$this->redirect(array('controller' => 'users', 'action' => 'login'));
		}
	}

	/**
	 * Index method.
	 *
	 * @version 0.1
	 * @since 0.1
	 */
	public function index() {
		$this->set('title_for_layout', __('Editor-Werkzeuge'));
	}

	/**
	 * Mailinglist method.
	 *
	 * @param season season to use (default: null == current season)
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	public function mailinglist($season = null) {

		$this->setAndGetStandard($season);

		$theSeparator = ',';
		if (!empty($this->request->data) && array_key_exists('ToolsEditor', $this->request->data)) {
			$theSeparator = $this->request->data['ToolsEditor']['separator'];
		}

		$this->set('separator', $theSeparator);

		$this->set('title_for_layout', __('Mailverteiler'));
	}

	/**
	 * Message method.
	 *
	 * @version 0.4
	 * @since 0.1
	 */
	public function message() {

		$this->set('title_for_layout', __('Nachricht versenden'));

		$this->setAndGetStandard(null);

		// create messages
		if (!empty($this->request->data)) {

			$this->set('messagedata', $this->request->data);

			$messageresult = array();

			$toMeOnly = $this->request->data['ToolsEditor']['recipient'] === 'i';
			$sendEmail = ($this->request->data['ToolsEditor']['recipient'] === 'a') ||
					($this->request->data['ToolsEditor']['recipient'] === 'm') ||
					$toMeOnly;
			$sendLetter = ($this->request->data['ToolsEditor']['recipient'] === 'a') ||
					($this->request->data['ToolsEditor']['recipient'] === 's') ||
					$toMeOnly;

			$sendSingleEmails = $this->request->data['ToolsEditor']['mailkind'] === 's';

			// referees to send messages to
			$hasMe = false;
			if (!$toMeOnly) {
				foreach ($this->viewVars['referees'] as $ref) {
					$arrReferees[] = $ref;
					if ($this->viewVars['userpersonid'] == $ref['Person']['id']) {
						$hasMe = true;
					}
				}
			}

			if (!$hasMe) {
				$arrReferees[] = $this->Referee->getRefereeByPersonId($this->viewVars['userpersonid'], $this->viewVars['isEditor']);
			}

			// if attachment: do all of them exist?
			$attachment = array();
			$attachfails = array();

			// directly prompted files
			if (!empty($this->request->data['ToolsEditor']['attachment'])) {

				foreach (explode(';', $this->request->data['ToolsEditor']['attachment']) as $attfile) {
					$attachment[] = sprintf('%s%s%s', TMP, Configure::read('RefMan.template.attachments.path'), trim($attfile));
				}
				foreach ($attachment as $attfile) {
					if (!file_exists($attfile)) {
						$attachfails[] = $attfile;
					}
				}

			}

			// generated person data
			if (!empty($this->request->data['ToolsEditor']['person_data'])) {
				foreach ($arrReferees as $attperson) {
					$tmpFile = sprintf('%s%s%s.pdf',
														 TMP,
														 Configure::read('RefMan.template.person-data.path'),
														 sprintf(Configure::read('RefMan.template.person-data.file'),
																		 RefManTemplate::fileName($attperson['Person']['name']),
																		 RefManTemplate::fileName($attperson['Person']['first_name']),
																		 $attperson['Person']['id']));
					if (!file_exists($tmpFile)) {
						$attachfails[] = $tmpFile;
					}
				}
			}

			// attachment error
			if (!empty($attachfails)) {
				$this->Session->setFlash(
																 __('Dateianhang "%s" existiert nicht. Keine Nachrichten wurden versendet.', implode('; ', $attachfails)),
																 'flash',
																 array('class' => 'danger')
																 );
			}

			// no attachment error - proceed
			if (empty($attachfails)) {

				// read templates
				$tplEmail = RefManTemplate::getTemplate('email');
				$tplLetter = RefManTemplate::getTemplate('letter.template');

				// start zip archive for letters
				if ($sendLetter) {
					RefManTemplate::openZip(Configure::read('RefMan.template.letter.output'));
				}

				// fill templates with form values
				$tplEmail = RefManTemplate::replace($tplEmail, 'body', $this->request->data['ToolsEditor']['body']);
				$tplLetter = RefManTemplate::replace($tplLetter, 'body', $this->request->data['ToolsEditor']['body']);

				$tplEmail = RefManTemplate::replace($tplEmail, 'opening', $this->request->data['ToolsEditor']['opening']);
				$tplLetter = RefManTemplate::replace($tplLetter, 'opening', $this->request->data['ToolsEditor']['opening']);

				$tplEmail = RefManTemplate::replace($tplEmail, 'closing', $this->request->data['ToolsEditor']['closing']);
				$tplLetter = RefManTemplate::replace($tplLetter, 'closing', $this->request->data['ToolsEditor']['closing']);

				$tplEmail = RefManTemplate::replace($tplEmail, 'signature', $this->request->data['ToolsEditor']['signature']);
				$tplLetter = RefManTemplate::replace($tplLetter, 'signature', $this->request->data['ToolsEditor']['signature']);

				$tplLetter = RefManTemplate::replace($tplLetter, 'subject', $this->request->data['ToolsEditor']['subject']);
				$tplLetter = RefManTemplate::replaceDateTimeData($tplLetter);

				$arrEmails = array();
				$arrLetter = array();

				// fill templates with person values
				$skipsend = !empty($this->request->data['ToolsEditor']['skiptill']);
				foreach ($arrReferees as $referee) {

					$persondatafile = sprintf(Configure::read('RefMan.template.person-data.file'),
																		RefManTemplate::fileName($referee['Person']['name']),
																		RefManTemplate::fileName($referee['Person']['first_name']),
																		$referee['Person']['id']);

					// attachments
					$attarray = $attachment;
					if (!empty($this->request->data['ToolsEditor']['person_data'])) {
						$attarray[] = sprintf('%s%s%s.pdf',
																	TMP,
																	Configure::read('RefMan.template.person-data.path'),
																	$persondatafile);
					}

					$contactEmail = RefManPeople::getPrimaryContact($referee, 'Email');
					if (!$skipsend && $sendEmail && !empty($contactEmail)) {

						$txtEmail = RefManTemplate::replaceRefereeData($tplEmail, $referee, 'text', 'html');

						// send email (set up email config correctly)
						$Email = new CakeEmail('default');
						$Email
								->to(array($contactEmail['Email']['email'] => RefManRefereeFormat::formatPerson($referee, 'fullname')))
								->subject($this->request->data['ToolsEditor']['subject']);

						// attachments
						if (!empty($attarray)) {
							$Email->attachments($attarray);
						}

						$sendsuccess = true;
						// if not testing...
						if (empty($this->request->data['ToolsEditor']['test_only'])) {
							try {

								$Email->send($txtEmail);

							} catch (Exception $e) {
								CakeLog::write('email', __('Error sending mail to %s <%s>: %s.',
																					 RefManRefereeFormat::formatPerson($referee, 'fullname'),
																					 $contactEmail['Email']['email'],
																					 $e->getMessage()));
								$messageresult[] = __('Fehler (%s &lt;%s&gt;): %s',
																			RefManRefereeFormat::formatPerson($referee, 'fullname'),
																			$contactEmail['Email']['email'],
																			$e->getMessage());
								$sendsuccess = false;
							}

						}

						if ($sendsuccess) {
								CakeLog::write('email', __('Mail sent to %s <%s>.',
																					 RefManRefereeFormat::formatPerson($referee, 'fullname'),
																					 $contactEmail['Email']['email']));

								// output for user
								$arrEmails[] = sprintf('%s &lt;%s&gt;',
																			 RefManRefereeFormat::formatPerson($referee, 'fullname'),
																			 $contactEmail['Email']['email']);
						}

					}

					$contactAddress = RefManPeople::getPrimaryContact($referee, 'Address');
					if (!$skipsend && $sendLetter && !empty($contactAddress) &&
							(empty($contactEmail) || ($referee['Referee']['docs_per_letter'] === true) || $toMeOnly)) {

						RefManTemplate::openMerge();

						// fill letter
						$txtLetter = $tplLetter;
						$txtLetter = RefManTemplate::replaceRefereeData($txtLetter, $referee, 'text', 'html');
						$txtLetter = RefManTemplate::replace($txtLetter, 'streetnumber', RefManRefereeFormat::formatAddress($contactAddress, 'streetnumber', 'text'));
						$txtLetter = RefManTemplate::replace($txtLetter, 'zipcity', RefManRefereeFormat::formatAddress($contactAddress, 'zipcity', 'text'));

						// store letter
						RefManTemplate::addToZip(sprintf('letter_%s.mmd', $persondatafile), $txtLetter, 'mmd');
						RefManTemplate::addToMerge(sprintf('letter_%s.pdf', $persondatafile), 'pdf');

						// attachments
						foreach ($attarray as $letterattachment) {
							RefManTemplate::addToMerge($letterattachment);
						}

						// output for user
						CakeLog::write('letter', __('Letter generated for %s.', RefManRefereeFormat::formatPerson($referee, 'fullname')));
						$arrLetter[] = sprintf('%s', RefManRefereeFormat::formatPerson($referee, 'fullname'));
						RefManTemplate::closeMerge(sprintf('merge_%s.tex', $persondatafile));
					}

					if ($skipsend && ($referee['Person']['name'] === $this->request->data['ToolsEditor']['skiptill'])) {
						$skipsend = false;
					}

				}

				// save zip archive for letters
				if ($sendLetter) {
					$zipfile = RefManTemplate::closeZip();
				}

				$messageresult[] = __('Emaillogs in "%slogs/email.log".', TMP);
				$messageresult[] = __('Brieflogs in "%slogs/letter.log".', TMP);

				if (empty($arrEmails)) {
					$messageresult[] = __('Keine Emails.');
				} else {
					if (!empty($this->request->data['ToolsEditor']['test_only'])) {
						$messageresult[] = __('Testmodus: keine Emails versendet.');
					}
					$messageresult[] = __('Emails (%d) an: %s.',
																count($arrEmails),
																RefManRefereeFormat::formatMultiline($arrEmails, ', '));
				}
				if (empty($arrLetter)) {
					$messageresult[] = __('Keine Briefe.');
				} else {
					$messageresult[] = __('Briefe (%d), abgelegt in "%s" an: %s.',
																count($arrLetter),
																$zipfile,
																RefManRefereeFormat::formatMultiline($arrLetter, ', '));
				}

				$this->Session->setFlash(
																 RefManRefereeFormat::formatMultiline($messageresult, '</p><p>'),
																 'flash',
																 array(
																			 'class' => 'success',
																			 'nohtml' => true,
																			 )
																 );

			}

		}

	}

	/**
	 * Set and get standard values.
	 *
	 * @param season season (default: null == current season)
	 *
	 * @version 0.3
	 * @since 0.1
	 */
	private function setAndGetStandard($season = null) {

		$theSeason = $this->getSeason($season);
		$this->set('seasonarray', $this->Season->getSeasonList($this->viewVars['isEditor']));

		$referees = $this->Referee->getReferees($theSeason, $this->viewVars['isEditor']);
		$this->set('referees', $referees);

		$this->set('controller', 'ToolsEditor');
	}

}

/* EOF */

