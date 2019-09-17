import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  // tslint:disable-next-line:jsx-self-close
  <NavDropdown icon="th-list" name="Entities" id="entity-menu">
    <MenuItem icon="asterisk" to="/entity/global-parameter-my-suffix">
      Global Parameter My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/bank-custody-my-suffix">
      Bank Custody My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/mi-my-suffix">
      Mi My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/dealer-participant-my-suffix">
      Dealer Participant My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/etf-product-my-suffix">
      Etf Product My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/etf-underlying-my-suffix">
      Etf Underlying My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/etf-underlying-dtl-my-suffix">
      Etf Underlying Dtl My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/etf-history-my-suffix">
      Etf History My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/subscript-my-suffix">
      Subscript My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/redemption-my-suffix">
      Redemption My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/etf-execution-my-suffix">
      Etf Execution My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/etf-execution-dtl-my-suffix">
      Etf Execution Dtl My Suffix
    </MenuItem>
    <MenuItem icon="asterisk" to="/entity/portofolio-my-suffix">
      Portofolio My Suffix
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
