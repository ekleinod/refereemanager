<!-- main navigation for big devices -->
<ul class="nav navbar-nav">
	<?php
		foreach ($navarray as $mainnav) {
			if (!array_key_exists('role', $mainnav) ||
					(array_key_exists('role', $mainnav) && ($mainnav['role'] === 'referee') && $isReferee) ||
					(array_key_exists('role', $mainnav) && ($mainnav['role'] === 'editor') && $isEditor)) {
	?>
				<?php
					if (array_key_exists('subnav', $mainnav)) {
				?>
						<li class="dropdown">
							<?php echo $this->Html->link(sprintf('%s <span class="caret"></span>', $mainnav['title']), $mainnav['routing'], array('class' => 'dropdown-toggle', 'data-toggle' => 'dropdown', 'role' => 'button', 'aria-expanded' => 'false', 'escape' => false)); ?>
							<ul class="dropdown-menu" role="menu">
								<?php
									foreach ($mainnav['subnav'] as $subnav) {
										if (!array_key_exists('role', $subnav) ||
												(array_key_exists('role', $subnav) && ($subnav['role'] === 'referee') && $isReferee) ||
												(array_key_exists('role', $subnav) && ($subnav['role'] === 'editor') && $isEditor)) {
								?>
											<?php
												if (array_key_exists('divider', $subnav)) {
											?>
													<li class="divider"></li>
											<?php
												} else {
													$active = '';
													if (($this->params['controller'] === $subnav['routing']['controller']) && ($this->params['action'] === $subnav['routing']['action'])) {
														$active = 'active';
													}
											?>
													<li class="<?php echo $active; ?>"><?php echo $this->Html->link($subnav['title'], $subnav['routing']); ?></li>
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
						$active = '';
						if (($this->params['controller'] === $mainnav['routing']['controller']) && ($this->params['action'] === $mainnav['routing']['action'])) {
							$active = 'active';
						}
				?>
					<li class="<?php echo $active; ?>"><?php echo $this->Html->link($mainnav['title'], $mainnav['routing']); ?></li>
				<?php
					}
				?>
	<?php
			}
		}
	?>
</ul>

