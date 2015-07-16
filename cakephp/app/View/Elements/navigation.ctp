<!-- main navigation for big devices -->
<ul>
	<?php
		foreach ($navarray as $mainnav) {
			if (!array_key_exists('role', $mainnav) ||
					(array_key_exists('role', $mainnav) && ($mainnav['role'] === 'editor') && $isEditor)) {
	?>
				<li><?php echo $this->Html->link($mainnav['title'], $mainnav['routing']); ?>
					<?php
						if (array_key_exists('subnav', $mainnav)) {
					?>
							<ul>
								<?php
									foreach ($mainnav['subnav'] as $subnav) {
										if (!array_key_exists('role', $subnav) ||
												(array_key_exists('role', $subnav) && ($subnav['role'] === 'referee') && $isReferee)) {
								?>
											<li><?php echo $this->Html->link($subnav['title'], $subnav['routing']); ?></li>
								<?php
										}
									}
								?>
							</ul>
					<?php
						}
					?>
				</li>
	<?php
			}
		}
	?>
</ul>

