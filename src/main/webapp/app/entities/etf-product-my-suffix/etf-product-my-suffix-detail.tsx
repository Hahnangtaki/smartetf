import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './etf-product-my-suffix.reducer';
import { IEtfProductMySuffix } from 'app/shared/model/etf-product-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEtfProductMySuffixDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EtfProductMySuffixDetail extends React.Component<IEtfProductMySuffixDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { etfProductEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            EtfProduct [<b>{etfProductEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="productCode">Product Code</span>
            </dt>
            <dd>{etfProductEntity.productCode}</dd>
            <dt>
              <span id="productName">Product Name</span>
            </dt>
            <dd>{etfProductEntity.productName}</dd>
            <dt>
              <span id="fundCategory">Fund Category</span>
            </dt>
            <dd>{etfProductEntity.fundCategory}</dd>
            <dt>
              <span id="inceptionDate">Inception Date</span>
            </dt>
            <dd>
              <TextFormat value={etfProductEntity.inceptionDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="clearingHouse">Clearing House</span>
            </dt>
            <dd>{etfProductEntity.clearingHouse}</dd>
            <dt>
              <span id="exchage">Exchage</span>
            </dt>
            <dd>{etfProductEntity.exchage}</dd>
            <dt>
              <span id="unitCreation">Unit Creation</span>
            </dt>
            <dd>{etfProductEntity.unitCreation}</dd>
            <dt>
              <span id="unitPriceInitial">Unit Price Initial</span>
            </dt>
            <dd>{etfProductEntity.unitPriceInitial}</dd>
            <dt>
              <span id="unitPriceCurr">Unit Price Curr</span>
            </dt>
            <dd>{etfProductEntity.unitPriceCurr}</dd>
            <dt>
              <span id="lotPerBasket">Lot Per Basket</span>
            </dt>
            <dd>{etfProductEntity.lotPerBasket}</dd>
            <dt>
              <span id="indexReff">Index Reff</span>
            </dt>
            <dd>{etfProductEntity.indexReff}</dd>
            <dt>
              <span id="rating">Rating</span>
            </dt>
            <dd>{etfProductEntity.rating}</dd>
            <dt>
              <span id="fee">Fee</span>
            </dt>
            <dd>{etfProductEntity.fee}</dd>
            <dt>Dealer Participant</dt>
            <dd>
              {etfProductEntity.dealerParticipants
                ? etfProductEntity.dealerParticipants.map((val, i) => (
                    <span key={val.id}>
                      <a>{val.id}</a>
                      {i === etfProductEntity.dealerParticipants.length - 1 ? '' : ', '}
                    </span>
                  ))
                : null}
            </dd>
            <dt>Mi</dt>
            <dd>{etfProductEntity.miId ? etfProductEntity.miId : ''}</dd>
            <dt>Bank Custody</dt>
            <dd>{etfProductEntity.bankCustodyId ? etfProductEntity.bankCustodyId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/etf-product-my-suffix" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/etf-product-my-suffix/${etfProductEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ etfProduct }: IRootState) => ({
  etfProductEntity: etfProduct.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EtfProductMySuffixDetail);
