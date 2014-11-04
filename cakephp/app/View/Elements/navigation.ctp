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
												(array_key_exists('role', $subnav) && ($subnav['role'] === 'editor') && $isEditor)) {
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

<!-- main navigation for small devices -->
<select onchange="if (this.value) window.location.href=this.value">
	<option selected="selected" value="">Go to...</option>
	<?php
		foreach ($navarray as $mainnav) {
			if (!array_key_exists('role', $mainnav) ||
					(array_key_exists('role', $mainnav) && ($mainnav['role'] === 'editor') && $isEditor)) {
	?>
				<option value="<?php echo Router::url($mainnav['routing']); ?>"><?php echo $mainnav['title']; ?></option>
					<?php
						if (array_key_exists('subnav', $mainnav)) {
							foreach ($mainnav['subnav'] as $subnav) {
								if (!array_key_exists('role', $subnav) ||
										(array_key_exists('role', $subnav) && ($subnav['role'] === 'editor') && $isEditor)) {
					?>
									<option value="<?php echo Router::url($subnav['routing']); ?>">- <?php echo $subnav['title']; ?></option>
					<?php
								}
							}
						}
					?>
	<?php
			}
		}
	?>
</select>

