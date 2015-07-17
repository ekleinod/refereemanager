<!-- main navigation for big devices -->
<ul class="nav navbar-nav">
	<?php
		foreach ($navarray as $mainnav) {
			if (!array_key_exists('role', $mainnav) ||
					(array_key_exists('role', $mainnav) && ($mainnav['role'] === 'editor') && $isEditor)) {
	?>
				<?php
					if (array_key_exists('subnav', $mainnav)) {
				?>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">xxx <span class="caret"></span>
							</a>
							<ul class="dropdown-menu" role="menu">
								<?php
									foreach ($mainnav['subnav'] as $subnav) {
										if (!array_key_exists('role', $subnav) ||
												(array_key_exists('role', $subnav) && ($subnav['role'] === 'referee') && $isReferee)) {
								?>
											<?php
												if (array_key_exists('divider', $subnav)) {
											?>
													<li class="divider"></li>
											<?php
												} else {
											?>
													<li><?php echo $this->Html->link($subnav['title'], $subnav['routing']); ?></li>
											<?php
												}
											?>
								<?php
										}
									}
								?>
							</ul>
						</li>
				<?php
					} else {
						// <li class="active">
				?>
					<li><?php echo $this->Html->link($mainnav['title'], $mainnav['routing']); ?></li>
				<?php
					}
				?>
	<?php
			}
		}
	?>
</ul>

